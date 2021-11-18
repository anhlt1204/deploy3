package com.esdo.bepilot.Repository;

import com.esdo.bepilot.Model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE User u SET u.name = ?2, u.phone = ?3 where u.id = ?1 ")
    void saveUser(Long id, String name , String phone) ;

    @Query(value = "SELECT * from users u ORDER BY u.id DESC limit 1", nativeQuery = true)
    List<User> getUserClone() ;

}
