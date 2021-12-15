package com.mobsys.easyStocks.persistence.repository;

import com.mobsys.easyStocks.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByMail(String mail);
}
