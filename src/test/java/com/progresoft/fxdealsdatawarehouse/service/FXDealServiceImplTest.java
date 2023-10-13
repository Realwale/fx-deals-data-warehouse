package com.progresoft.fxdealsdatawarehouse.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.progresoft.fxdealsdatawarehouse.dto.request.FXDealRequest;
import com.progresoft.fxdealsdatawarehouse.dto.response.FXDealResponse;
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
    void testSaveFXDeal_AlreadyExists() {
        FXDealRequest request = new FXDealRequest("123", "...", "...", null, null);
        when(fxDealRepository.existsByUniqueId("123")).thenReturn(true);

        FXDealResponse response = fxDealService.saveFXDeal(request);

        assertEquals(HttpStatus.CONFLICT.value(), response.getStatusCode());
        assertTrue(response.isError());
        assertFalse(response.isSuccess());
    }

    @Test
    void testSaveFXDeal_Success() {
        FXDealRequest request = new FXDealRequest("123", "...", "...", null, null);
        when(fxDealRepository.existsByUniqueId("123")).thenReturn(false);

        FXDealResponse response = fxDealService.saveFXDeal(request);

        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
        assertFalse(response.isError());
        assertTrue(response.isSuccess());
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

        List<FXDealResponse> responses = fxDealService.getAllFXDeals();

        assertEquals(2, responses.size());
        assertTrue(responses.stream().allMatch(r -> r.getStatusCode() == HttpStatus.OK.value()));
    }
}
