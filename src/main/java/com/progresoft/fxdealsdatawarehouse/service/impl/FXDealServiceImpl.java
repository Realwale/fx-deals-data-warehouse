package com.progresoft.fxdealsdatawarehouse.service.impl;

import com.progresoft.fxdealsdatawarehouse.dto.request.FXDealRequest;
import com.progresoft.fxdealsdatawarehouse.dto.response.FXDealResponse;
import com.progresoft.fxdealsdatawarehouse.exception.DuplicateDealException;
import com.progresoft.fxdealsdatawarehouse.exception.NotFoundException;
import com.progresoft.fxdealsdatawarehouse.model.FXDeal;
import com.progresoft.fxdealsdatawarehouse.repository.FXDealRepository;
import com.progresoft.fxdealsdatawarehouse.service.FXDealService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@RequiredArgsConstructor
@Slf4j
public class FXDealServiceImpl implements FXDealService {

    private final FXDealRepository fxDealRepository;

    @Override
    public FXDeal saveFXDeal(FXDealRequest fxDealRequest) {

            if (fxDealRepository.existsByUniqueId(fxDealRequest.getUniqueId())) {
                log.warn("Attempt to save a duplicate deal with ID {}", fxDealRequest.getUniqueId());
                throw new DuplicateDealException("Deal already exists with ID: " + fxDealRequest.getUniqueId());
            }

            FXDeal deal = FXDeal.builder()
                    .uniqueId(fxDealRequest.getUniqueId())
                    .fromCurrency(fxDealRequest.getFromCurrency())
                    .toCurrency(fxDealRequest.getToCurrency())
                    .dealTimestamp(fxDealRequest.getDealTimestamp())
                    .dealAmount(fxDealRequest.getDealAmount())
                    .build();

            fxDealRepository.save(deal);
            log.info("Saved deal with ID {}", deal.getUniqueId());

        return  deal;
    }


    @Override
    public FXDealResponse getFXDealByUniqueId(String uniqueId) {
        FXDeal deal = fxDealRepository.findFXDealByUniqueId(uniqueId)
                .orElseThrow(() -> {
                    log.error("No deal found with ID: {}", uniqueId);
                    return new NotFoundException("No deal found with ID: " + uniqueId);
                });

        return new FXDealResponse(true, false, deal, HttpStatus.OK.value());
    }

    @Override
    public List<FXDeal> getAllFXDeals() {

        return fxDealRepository.findAll();

    }

}



