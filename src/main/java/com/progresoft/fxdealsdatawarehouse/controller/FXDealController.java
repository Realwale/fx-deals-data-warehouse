package com.progresoft.fxdealsdatawarehouse.controller;

import com.progresoft.fxdealsdatawarehouse.dto.request.FXDealRequest;
import com.progresoft.fxdealsdatawarehouse.dto.response.FXDealResponse;
import com.progresoft.fxdealsdatawarehouse.model.FXDeal;
import com.progresoft.fxdealsdatawarehouse.service.FXDealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/fx-deal")
@RequiredArgsConstructor
public class FXDealController {

    private final FXDealService fxDealService;

    @PostMapping("/import")
    public ResponseEntity<FXDeal> saveFXDeal(@RequestBody FXDealRequest fxDealRequest) {
        fxDealRequest.validateDeal();
        FXDeal deal = fxDealService.saveFXDeal(fxDealRequest);
        return new ResponseEntity<>(deal, HttpStatus.CREATED);
    }


   @GetMapping("/{uniqueId}")
   public ResponseEntity<FXDealResponse> getFXDeal(@PathVariable String uniqueId) {
        FXDealResponse response = fxDealService.getFXDealByUniqueId(uniqueId);

        return ResponseEntity.status(response.getStatusCode()).body(response);
  }

   @GetMapping
   public ResponseEntity<List<FXDeal>> getAllFXDeals() {
        List<FXDeal> deals = fxDealService.getAllFXDeals();
         return ResponseEntity.status(HttpStatus.OK).body(deals);
  }

}










