package com.progresoft.fxdealsdatawarehouse.service;


import com.progresoft.fxdealsdatawarehouse.dto.request.FXDealRequest;
import com.progresoft.fxdealsdatawarehouse.dto.response.FXDealResponse;

import java.util.List;


public interface FXDealService {
    FXDealResponse saveFXDeal(FXDealRequest fxDealRequest);

    FXDealResponse getFXDealByUniqueId(String uniqueId);

    List<FXDealResponse> getAllFXDeals();
}
