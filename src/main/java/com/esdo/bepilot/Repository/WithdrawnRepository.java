package com.esdo.bepilot.Repository;

import com.esdo.bepilot.Model.Entity.MissionDetail;
import com.esdo.bepilot.Model.Entity.Withdrawn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawnRepository extends JpaRepository<Withdrawn , Long> {

    @Query(value = "SELECT t FROM Withdrawn  t where t.user.id = ?1 " )
    Page<Withdrawn> getByUserId(Pageable pageable, Long id) ;
}
