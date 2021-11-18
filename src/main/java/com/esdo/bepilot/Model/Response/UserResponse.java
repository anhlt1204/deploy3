package com.esdo.bepilot.Model.Response;

import com.esdo.bepilot.Model.Entity.Withdrawn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;

    private String userKey;

    private String idAccount ;

    private String avatar;

    private String name;

    private String phone ;

    private String email ;

    private BigDecimal amountMoneyReceive;

    private BigDecimal moneyWithdrawn;

    private BigDecimal moneyRemaining;

    private int numberOfMissionDone;

    private List<MissionDetailResponse> missionDetailList = new ArrayList<>() ;
    
    private List<WithdrawnResponse> withdrawnList = new ArrayList<>() ;
}
