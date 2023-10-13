package com.progresoft.fxdealsdatawarehouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FXDeal {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true)
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
}
