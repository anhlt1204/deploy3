package com.esdo.bepilot.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissionDetailResponse {
    private Long id;
    private Long missionId;
    private Long userId;
    private String status;
}
