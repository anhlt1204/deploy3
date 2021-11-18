package com.esdo.bepilot.Model.Request;

import com.esdo.bepilot.Model.Entity.MissionDetail;
import com.esdo.bepilot.Model.Entity.Withdrawn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotNull
    private String userKey;

    private String idAccount ;
    @NotNull
    private String avatar;

    @NotNull
    private String name;

    @NotNull
    private String phone;

    @NotNull
    private String email ;

    private BigDecimal amountMoneyReceive;

    private BigDecimal moneyWithdrawn;

    private BigDecimal moneyRemaining;


}
