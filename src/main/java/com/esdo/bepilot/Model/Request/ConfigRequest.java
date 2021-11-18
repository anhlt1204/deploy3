package com.esdo.bepilot.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigRequest {
    private int ytKeyMinTime;
    private int ytKeyMaxTime;
    private float ytKeyCustomerPay;
    private float ytKeyUserReceived;

    private int ggKeyMinTime;
    private int ggKeyMaxTime;
    private float ggKeyCustomerPay;
    private float ggKeyUserReceived;

    private int ggBackLinkMinTime;
    private int ggBackLinkMaxTime;
    private float ggBackLinkCustomerPay;
    private float ggBackLinkUserReceived;

    private int ggMissionMinTime;
    private int ggMissionMaxTime;
    private float ggMissionCustomerPay;
    private float ggMissionUserReceived;

    private Integer maxMission;
    private Integer missionLifeCycle;
    private Boolean isRepeat;
}
