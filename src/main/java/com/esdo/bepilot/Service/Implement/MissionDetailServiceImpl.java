package com.esdo.bepilot.Service.Implement;

import com.esdo.bepilot.Model.Entity.Customer;
import com.esdo.bepilot.Model.Entity.MissionDetail;
import com.esdo.bepilot.Model.Response.CustomerResponse;
import com.esdo.bepilot.Model.Response.MissionDetailResponse;
import com.esdo.bepilot.Repository.MissionDetailRepository;
import com.esdo.bepilot.Service.Mapper.MissionDetailMapper;
import com.esdo.bepilot.Service.MissionDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Slf4j
public class MissionDetailServiceImpl implements MissionDetailService {

    @Autowired
    public MissionDetailRepository missionDetailRepository ;

    @Autowired
    public MissionDetailMapper mapper ;

    public MissionDetail create(MissionDetail missionDetail){
        log.info("Inside create of MissionDetail Service ");
//        missionDetailRepository.save(missionDetail) ;
        return null ;
    }

    public List<MissionDetail> getAllMissionDetail(){
        log.info("Inside getAllMissionDetail of MissionDetail Service ");
//        missionDetailRepository.findAll() ;
        return null ;
    }

    public List<MissionDetailResponse> getMissionDetailByUserId(int pageIndex , int pageSize ,Long id) {
        log.info("Inside getMissionDetailById of MissionDetail Service ");
        Pageable paging = PageRequest.of(pageIndex, pageSize);
        Page<MissionDetail> page = missionDetailRepository.getByUserId(paging,id) ;
        List<MissionDetail> customers = page.getContent();
        List<MissionDetailResponse> responses = mapper.mapToListMissionDetail(customers) ;
        return responses ;
    }

    public String deleteMissionDetailById(Long id) {
        log.info("Inside deleteMissionDetailById of MissionDetail Service ");
//        missionDetailRepository.deleteById(id);
        return "" ;
    }

    public MissionDetail updateMissionDetailById(MissionDetail newMissionDetail) {
        log.info("Inside updateMissionDetailById of MissionDetail Service ");
//        missionDetailRepository.save(newMissionDetail);
        return null ;
    }
}