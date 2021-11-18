package com.esdo.bepilot.Service;

import com.esdo.bepilot.Model.Entity.Customer;
import com.esdo.bepilot.Model.Entity.Mission;
import com.esdo.bepilot.Model.Request.MissionRequest;
import com.esdo.bepilot.Model.Response.ListMissionResponse;
import com.esdo.bepilot.Model.Response.MissionResponse;

import java.util.List;

public interface MissionService {

    ListMissionResponse getListMission(int pageIndex, int pageSize);

    MissionResponse createMission(MissionRequest missionRequest);

    MissionResponse updateMissionById(Long missionId, MissionRequest missionRequest);

    ListMissionResponse searchMission(String name,
                                        String communication,
                                        int pageIndex,
                                        int pageSize);

    Customer findCustomerById(Long id);

    List<Mission> findByStatus(String status,Long customerId);

    void deleteMissionById(Long missionId);

}