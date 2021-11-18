package com.esdo.bepilot.Service.Mapper;

import com.esdo.bepilot.Model.Entity.Mission;
import com.esdo.bepilot.Model.Enum.MissionStatus;
import com.esdo.bepilot.Model.Request.MissionRequest;
import com.esdo.bepilot.Model.Response.MissionResponse;
import com.esdo.bepilot.Service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class MissionMapper {

    @Autowired
    MissionService missionService;

    @Autowired
    MissionDetailMapper missionDetailMapper;

    public List<MissionResponse> mapToListResponse(List<Mission> missions) {
        List<MissionResponse> missionResponses = new ArrayList<>();
        missions.forEach(mission -> missionResponses.add(mapToMissionResponse(mission)));
        return missionResponses;
    }

    public List<Mission> map(List<MissionResponse> missionResponses) {
        List<Mission> missions = new ArrayList<>();
        missionResponses.forEach(missionResponse -> missions.add(mapToMissionStatus(missionResponse)));
        return missions;
    }

    /**
     * Map dữ liệu từ response sang entity
     *
     * @param missionResponse
     * @return
     */
    private Mission mapToMissionStatus(MissionResponse missionResponse) {
        Mission mission = new Mission();
        if (Objects.equals(mission.getQuantity(), mission.getQuantityMade())) {
            mission.setStatus(MissionStatus.COMPLETE.toString());
        }
        if (mission.getQuantityMade() != 0 && mission.getQuantityMade() < mission.getQuantity()) {
            mission.setStatus(MissionStatus.RUNNING.toString());
        }
        mission.setMissionType(missionResponse.getMissionType());
        mission.setCommunication(missionResponse.getCommunication());
        mission.setId(missionResponse.getId());
        mission.setKeyWord(missionResponse.getKeyWord());
        mission.setLink(missionResponse.getLink());
        mission.setName(missionResponse.getName());
        mission.setMoneyReceived(missionResponse.getMoneyReceived());
        mission.setPriceUnit(missionResponse.getPriceUnit());
        mission.setQuantity(missionResponse.getQuantity());
        mission.setQuantityMade(missionResponse.getQuantityMade());
        mission.setCustomer(missionService.findCustomerById(missionResponse.getCustomerId()));
        return mission;
    }

    /**
     * Map dữ liệu từ entity sang response
     *
     * @param mission
     * @return
     */
    public MissionResponse mapToMissionResponse(Mission mission) {
        MissionResponse missionResponse = new MissionResponse();
        missionResponse.setMissionKey(mission.getMissionKey());
        missionResponse.setMissionType(mission.getMissionType());
        missionResponse.setCommunication(mission.getCommunication());
        missionResponse.setId(mission.getId());
        missionResponse.setKeyWord(mission.getKeyWord());
        missionResponse.setLink(mission.getLink());
        missionResponse.setName(mission.getName());
        missionResponse.setCreateAt(mission.getCreateAt());
        missionResponse.setUpdateAt(mission.getUpdateAt());
        missionResponse.setDeadTime(mission.getDeadTime());
        missionResponse.setMoneyReceived(mission.getMoneyReceived());
        missionResponse.setPriceUnit(mission.getPriceUnit());
        missionResponse.setQuantity(mission.getQuantity());
        missionResponse.setQuantityMade(mission.getQuantityMade());
        missionResponse.setCustomerId(mission.getCustomer().getId());
        missionResponse.setMissionDetails(missionDetailMapper.mapToListMissionDetail(mission.getMissionDetails()));
        return missionResponse;
    }

    /**
     * Map dữ liệu từ request sang entity
     *
     * @param missionRequest
     * @return
     */
    public Mission mapToMission(MissionRequest missionRequest) {
        Mission mission = new Mission();
        mission.setCustomer(missionService.findCustomerById(missionRequest.getCustomerId()));
        mission.setId(missionRequest.getId());
        mission.setStatus(MissionStatus.NOT_RUN.toString());
        mission.setDeadTime(7);
        mission.setMissionType(missionRequest.getMissionType());
        mission.setCommunication(missionRequest.getCommunication());
        mission.setKeyWord(missionRequest.getKeyWord());
        mission.setLink(missionRequest.getLink());
        mission.setName(missionRequest.getName());
        mission.setMoneyReceived(missionRequest.getMoneyReceived());
        mission.setPriceUnit(missionRequest.getPriceUnit());
        mission.setQuantity(missionRequest.getQuantity());
        mission.setQuantityMade(missionRequest.getQuantityMade());
        mission.setMissionKey(missionRequest.getMissionKey());
        mission.setCreateAt(missionRequest.getCreateAt());
        return mission;
    }


}
