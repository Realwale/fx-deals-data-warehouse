package com.progresoft.fxdealsdatawarehouse.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.progresoft.fxdealsdatawarehouse.dto.request.FXDealRequest;
import com.progresoft.fxdealsdatawarehouse.dto.response.FXDealResponse;
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
        FXDealRequest request = new FXDealRequest(
                "123",
                "USD",
                "EUR",
                Instant.now(),
                BigDecimal.valueOf(1000)
        );
        FXDealResponse serviceResponse = new FXDealResponse(true, false, "SomeData", HttpStatus.CREATED.value());
        when(fxDealService.saveFXDeal(request)).thenReturn(serviceResponse);

        ResponseEntity<FXDealResponse> response = fxDealController.saveFXDeal(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(serviceResponse, response.getBody());
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
        FXDealResponse deal1 = new FXDealResponse(true, false, "SomeData1", HttpStatus.OK.value());
        FXDealResponse deal2 = new FXDealResponse(true, false, "SomeData2", HttpStatus.OK.value());
        when(fxDealService.getAllFXDeals()).thenReturn(Arrays.asList(deal1, deal2));

        ResponseEntity<List<FXDealResponse>> response = fxDealController.getAllFXDeals();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains(deal1));
        assertTrue(response.getBody().contains(deal2));
    }
}
