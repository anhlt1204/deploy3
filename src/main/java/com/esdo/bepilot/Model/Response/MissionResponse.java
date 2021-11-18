package com.esdo.bepilot.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MissionResponse {

    private Long id;

    private String missionKey;

    private String name;

    private String communication;

    private String missionType;

    private Integer quantity;

    private Integer quantityMade;

    private String keyWord;

    private String link;

    private BigDecimal priceUnit;

    private BigDecimal moneyReceived;

    private OffsetDateTime createAt;

    private OffsetDateTime updateAt;

    private Integer deadTime;

    private Long customerId ;

    private List<MissionDetailResponse> missionDetails = new ArrayList<>() ;
}
