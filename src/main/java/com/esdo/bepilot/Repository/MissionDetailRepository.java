package com.esdo.bepilot.Repository;

import com.esdo.bepilot.Model.Entity.MissionDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface MissionDetailRepository extends JpaRepository<MissionDetail, Long> {


    /*
    * lấy các missionDetail theo id của User
    *
    *
     */
    @Query(value = "SELECT t FROM MissionDetail  t where t.users.id = ?1 " )
    Page<MissionDetail> getByUserId(Pageable pageable, Long id) ;

}

