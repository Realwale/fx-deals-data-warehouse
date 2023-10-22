package com.progresoft.fxdealsdatawarehouse.service;


import com.progresoft.fxdealsdatawarehouse.dto.request.FXDealRequest;
import com.progresoft.fxdealsdatawarehouse.dto.response.FXDealResponse;
import com.progresoft.fxdealsdatawarehouse.model.FXDeal;

import java.util.List;


public interface FXDealService {
    FXDeal saveFXDeal(FXDealRequest fxDealRequest);

    FXDealResponse getFXDealByUniqueId(String uniqueId);

    List<FXDeal> getAllFXDeals();
}
