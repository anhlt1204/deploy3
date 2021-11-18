package com.esdo.bepilot.Service.Implement;

import com.esdo.bepilot.Exception.CustomException;
import com.esdo.bepilot.Exception.InvalidException;
import com.esdo.bepilot.Model.Entity.Customer;
import com.esdo.bepilot.Model.Entity.Employee;
import com.esdo.bepilot.Model.Entity.Mission;
import com.esdo.bepilot.Model.Request.MissionRequest;
import com.esdo.bepilot.Model.Response.EmployeeResponse;
import com.esdo.bepilot.Model.Response.ListMissionResponse;
import com.esdo.bepilot.Model.Response.MissionResponse;
import com.esdo.bepilot.Repository.CustomerRepository;
import com.esdo.bepilot.Repository.MissionRepository;
import com.esdo.bepilot.Service.Mapper.ConvertObject;
import com.esdo.bepilot.Service.Mapper.MissionMapper;
import com.esdo.bepilot.Service.MissionService;
import com.esdo.bepilot.Service.Validate.MissionValidate;
import com.esdo.bepilot.Specification.MissionSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MissionServiceImpl implements MissionService {

    @Autowired
    MissionRepository missionRepository;

    @Autowired
    MissionMapper missionMapper;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    MissionValidate missionValidate;

    /**
     * Lấy danh sách các nhiệm vụ
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public ListMissionResponse getListMission(int pageIndex, int pageSize) {
        ListMissionResponse response = new ListMissionResponse();

        Pageable paging = PageRequest.of(pageIndex, pageSize);
        Page<Mission> page = missionRepository.findAll(paging);
        List<Mission> missions = page.getContent();

        List<MissionResponse> missionResponseList = new ArrayList<>();
        for (Mission m : missions) {
            missionResponseList.add(missionMapper.mapToMissionResponse(m));
        }

        response.setMissionResponseList(missionResponseList);
        response.setPage(pageIndex);
        response.setSize(pageSize);
        response.setTotalPages(page.getTotalPages());
        response.setTotalItems((int) page.getTotalElements());
        return response;
    }

    /**
     * Tạo nhiệm vụ mới
     *
     * @param missionRequest
     * @return
     */
    @Override
    public MissionResponse createMission(MissionRequest missionRequest) {
        //validate đầu vào
        missionValidate.validate(missionRequest);

        Mission mission = missionMapper.mapToMission(missionRequest);
        missionRepository.save(mission);

        List<Mission> missions = missionRepository.getMissionKey();
        missions.get(0).setMissionKey("NV" + missions.get(0).getId());
        Mission missionCreated = missionRepository.save(missions.get(0));

        return missionMapper.mapToMissionResponse(missionCreated);
    }


    /**
     * Sửa nhiệm vụ theo id và check id theo id của khác hàng
     *
     * @param missionId
     * @param missionRequest
     * @return
     */
    @Override
    public MissionResponse updateMissionById(Long missionId, MissionRequest missionRequest) {
        //validate đầu vào
        missionValidate.validate(missionRequest);
        Mission mission = missionRepository.getById(missionId);
        OffsetDateTime time = mission.getCreateAt();
        Optional<Mission> optionalMission = missionRepository.findById(missionId);

        if (optionalMission.isEmpty()) {
            throw new InvalidException("Invalid mission has id = " + missionId);
        }
        missionRequest.setId(missionId);
        missionRequest.setUpdateAt(OffsetDateTime.now());
        Mission missionMap = missionMapper.mapToMission(missionRequest);
        missionMap.setCreateAt(time);
        Mission missionEdit = missionRepository.save(missionMap);

        return missionMapper.mapToMissionResponse(missionEdit);
    }

    /**
     * Tìm kiếm theo tên nhiệm vụ và nền tảng
     *
     * @param name
     * @param communication
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public ListMissionResponse searchMission(String name, String communication, int pageIndex, int pageSize) {
        ListMissionResponse response = new ListMissionResponse();

        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<Mission> page = missionRepository.findAll(MissionSpecification.filterMission(name, communication), pageable);
        List<Mission> missions = page.getContent();

        List<MissionResponse> missionResponseList = new ArrayList<>();
        for (Mission m : missions) {
            missionResponseList.add(missionMapper.mapToMissionResponse(m));
        }

        response.setMissionResponseList(missionResponseList);
        response.setPage(pageIndex);
        response.setSize(pageSize);
        response.setTotalPages(page.getTotalPages());
        response.setTotalItems((int) page.getTotalElements());
        return response;
    }

    /**
     * check id khách hàng có tồn tại không
     *
     * @param id
     * @return
     */
    @Override
    public Customer findCustomerById(Long id) {
        Optional<Customer> opt = customerRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new CustomException("Customer not found");
    }

    /**
     * Lấy danh sach nhiệm vụ theo trạng thái
     *
     * @param status
     * @param customerId
     * @return
     */
    @Override
    public List<Mission> findByStatus(String status, Long customerId) {
        List<MissionResponse> mission = missionRepository.findByStatus(status, customerId);
        return missionMapper.map(mission);
    }

    /**
     * Xóa nhiệm vụ theo id nhiệm vụ
     *
     * @param missionId
     */
    @Override
    public void deleteMissionById(Long missionId) {
        Optional<Mission> optionalMission = missionRepository.findById(missionId);

        if (optionalMission.isEmpty()) {
            throw new InvalidException("Invalid mission has id = " + missionId);
        }
        missionRepository.deleteById(missionId);
    }

}