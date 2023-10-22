package com.progresoft.fxdealsdatawarehouse.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import com.progresoft.fxdealsdatawarehouse.dto.request.FXDealRequest;
import com.progresoft.fxdealsdatawarehouse.dto.response.FXDealResponse;
import com.progresoft.fxdealsdatawarehouse.model.FXDeal;
import com.progresoft.fxdealsdatawarehouse.service.FXDealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

class FXDealControllerTest {

    @Mock
    private FXDealService fxDealService;

    @InjectMocks
    private FXDealController fxDealController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveFXDeal_Success() {
        Instant testInstant = Instant.now();
        FXDealRequest request = new FXDealRequest(
                UUID.randomUUID().toString(),
                "USD",
                "EUR",
                testInstant,
                BigDecimal.valueOf(1000)
        );
        FXDeal expectedDeal = new FXDeal(
                request.getUniqueId(),
                request.getFromCurrency(),
                request.getToCurrency(),
                testInstant,
                request.getDealAmount()
        );
        when(fxDealService.saveFXDeal(request)).thenReturn(expectedDeal);

        ResponseEntity<FXDeal> responseEntity = fxDealController.saveFXDeal(request);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(expectedDeal, responseEntity.getBody());

    }

    @Test
    void testGetFXDeal_Success() {
        String uniqueId = "123";
        FXDealResponse serviceResponse = new FXDealResponse(true, false, "SomeData", HttpStatus.OK.value());
        when(fxDealService.getFXDealByUniqueId(uniqueId)).thenReturn(serviceResponse);

        ResponseEntity<FXDealResponse> response = fxDealController.getFXDeal(uniqueId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(serviceResponse, response.getBody());
    }

    @Test
    void testGetAllFXDeals() {
        FXDeal deal1 = new FXDeal();
        FXDeal deal2 = new FXDeal();
        when(fxDealService.getAllFXDeals()).thenReturn(Arrays.asList(deal1, deal2));

        ResponseEntity<List<FXDeal>> deals = fxDealController.getAllFXDeals();

        assertEquals(HttpStatus.OK, deals.getStatusCode());
        assertTrue(deals.getBody().contains(deal1));
        assertTrue(deals.getBody().contains(deal2));
    }

}
