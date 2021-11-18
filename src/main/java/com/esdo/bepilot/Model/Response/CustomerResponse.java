package com.esdo.bepilot.Model.Response;

import com.esdo.bepilot.Model.Entity.TransactionOfCustomer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

    private Long id;

    private String CustomerKey;

    private String avatar;

    private String name;

    private String phone;

    private String email ;

    private BigDecimal moneyRemaining;

    private BigDecimal moneyAvailable ;

    private String companyName;

    private List<MissionResponse> missionList = new ArrayList<>();

    private List<TransactionOfCustomerResponse> transaction = new ArrayList<>();

}
