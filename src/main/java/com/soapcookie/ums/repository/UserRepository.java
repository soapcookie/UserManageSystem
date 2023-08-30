package com.soapcookie.ums.repository;

import com.soapcookie.ums.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User FindByName(String name);

    User FindById(long id);

    User FindByEmail(String email);
}
