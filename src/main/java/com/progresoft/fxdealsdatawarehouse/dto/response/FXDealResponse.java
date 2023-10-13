package com.progresoft.fxdealsdatawarehouse.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@JsonPropertyOrder(value = {"success", "error", "data", "statusCode"})
public class FXDealResponse {


    private boolean success;
    private boolean error;
    private Object data;
    @JsonProperty("status_code")
    private int statusCode;


    public FXDealResponse(boolean success, boolean error, Object data, int statusCode) {
        this.success = success;
        this.error = error;
        this.data = data;
        this.statusCode = statusCode;
    }
 }

