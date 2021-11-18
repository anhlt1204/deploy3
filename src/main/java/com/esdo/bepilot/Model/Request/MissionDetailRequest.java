package com.esdo.bepilot.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissionDetailRequest {
    private Long id;
    private Long missionId;
    private Long userId;
    private String status;
}
