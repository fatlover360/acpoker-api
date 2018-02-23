package com.acpoker.acpokerapi.repository;

import com.acpoker.acpokerapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserByUid(String uid);
}
