package com.progresoft.fxdealsdatawarehouse.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.progresoft.fxdealsdatawarehouse.dto.request.FXDealRequest;
import com.progresoft.fxdealsdatawarehouse.dto.response.FXDealResponse;
import com.progresoft.fxdealsdatawarehouse.exception.DuplicateDealException;
import com.progresoft.fxdealsdatawarehouse.exception.NotFoundException;
import com.progresoft.fxdealsdatawarehouse.model.FXDeal;
import com.progresoft.fxdealsdatawarehouse.repository.FXDealRepository;
import com.progresoft.fxdealsdatawarehouse.service.impl.FXDealServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class FXDealServiceImplTest {

    @Mock
    private FXDealRepository fxDealRepository;

    @InjectMocks
    private FXDealServiceImpl fxDealService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveFXDeal_ShouldSaveAndReturnDeal() {
        FXDealRequest request = new FXDealRequest("123", "USD", "EUR", Instant.now(), new BigDecimal(100));

        when(fxDealRepository.existsByUniqueId(anyString())).thenReturn(false);
        when(fxDealRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        FXDeal deal = fxDealService.saveFXDeal(request);

        assertEquals("123", deal.getUniqueId());
    }

    @Test
    void saveFXDeal_ShouldThrowDuplicateDealException() {
        FXDealRequest request = new FXDealRequest("123", "USD", "EUR", Instant.now(), new BigDecimal(100));

        when(fxDealRepository.existsByUniqueId(anyString())).thenReturn(true);

        assertThrows(DuplicateDealException.class, () -> {
            fxDealService.saveFXDeal(request);
        });
    }

    @Test
    void testGetFXDealByUniqueId_NotFound() {
        when(fxDealRepository.findFXDealByUniqueId("123")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> fxDealService.getFXDealByUniqueId("123"));
    }

    @Test
    void testGetFXDealByUniqueId_Found() {
        FXDeal deal = new FXDeal();
        when(fxDealRepository.findFXDealByUniqueId("123")).thenReturn(Optional.of(deal));

        FXDealResponse response = fxDealService.getFXDealByUniqueId("123");

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertFalse(response.isError());
        assertTrue(response.isSuccess());
    }

    @Test
    void testGetAllFXDeals() {

        FXDeal deal1 = new FXDeal();
        FXDeal deal2 = new FXDeal();
        when(fxDealRepository.findAll()).thenReturn(Arrays.asList(deal1, deal2));

        List<FXDeal> deals = fxDealService.getAllFXDeals();

        assertEquals(2, deals.size());
        assertTrue(deals.contains(deal1));
        assertTrue(deals.contains(deal2));
    }
}
