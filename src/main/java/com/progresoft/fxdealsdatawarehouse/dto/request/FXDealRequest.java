package com.progresoft.fxdealsdatawarehouse.dto.request;



import com.fasterxml.jackson.annotation.JsonProperty;
import com.progresoft.fxdealsdatawarehouse.exception.ValidationException;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FXDealRequest {


    private static final int ISO_CODE_LENGTH = 3;

    @JsonProperty("unique_id")
    private String uniqueId;

    @JsonProperty("from_currency")
    private String fromCurrency;

    @JsonProperty("to_currency")
    private String toCurrency;

    @JsonProperty("deal_timestamp")
    private Instant dealTimestamp;

    @JsonProperty("deal_amount")
    private BigDecimal dealAmount;


    public void validateDeal() {

        List<String> errors = new ArrayList<>();

            validateUniqueId(uniqueId, errors);
            validateCurrencyISO(fromCurrency, "Invalid From Currency ISO Code", errors);
            validateCurrencyISO(toCurrency, "Invalid To Currency ISO Code", errors);
            validateTimestamp(dealTimestamp, errors);
            validateDealAmount(dealAmount, errors);

            if (!errors.isEmpty()) {
                throw new ValidationException(String.join(", ", errors));
            }
        }

        private static void validateUniqueId(String uniqueId, List<String> errors) {
            if (uniqueId == null || uniqueId.trim().isEmpty()) {
                errors.add("Deal Unique ID is missing or empty");
            }
        }

        private static void validateCurrencyISO(String currency, String errorMessage, List<String> errors) {
            if (currency == null || currency.length() != ISO_CODE_LENGTH) {
                errors.add(errorMessage);
            }
        }

        private static void validateTimestamp(Instant timestamp, List<String> errors) {
            if (timestamp == null) {
                errors.add("Deal timestamp is missing");
            }
        }

        private static void validateDealAmount(BigDecimal amount, List<String> errors) {
            if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
                errors.add("Invalid deal amount");
            }
        }
    }


