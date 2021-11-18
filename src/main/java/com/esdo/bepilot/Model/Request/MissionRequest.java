package com.esdo.bepilot.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissionRequest {
    private Long id;

    private String missionKey;

    private String name;

    private String communication;

    private String missionType;

    private Integer quantity;

    private Integer quantityMade;

    private String keyWord;

    private String link;

    private Long customerId;

    private Long missionDetails;

    private BigDecimal priceUnit;

    private BigDecimal moneyReceived;

    private OffsetDateTime updateAt;

    private OffsetDateTime createAt;
}
