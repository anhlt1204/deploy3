package com.esdo.bepilot.Repository;

import com.esdo.bepilot.Model.Entity.SubConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubConfigRepository extends JpaRepository<SubConfig, Long> {
}
