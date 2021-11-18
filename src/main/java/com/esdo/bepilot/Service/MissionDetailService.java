package com.esdo.bepilot.Service;

import com.esdo.bepilot.Model.Entity.MissionDetail;
import com.esdo.bepilot.Model.Response.MissionDetailResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MissionDetailService {

    MissionDetail create(MissionDetail missionDetail);

    List<MissionDetail> getAllMissionDetail();

    List<MissionDetailResponse> getMissionDetailByUserId(int pageIndex , int pageSize, Long id);

    String deleteMissionDetailById(Long id) ;

    MissionDetail updateMissionDetailById(MissionDetail newMissionDetail) ;
}
