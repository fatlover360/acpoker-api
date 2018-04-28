package com.acpoker.acpokerapi.repository;

import com.acpoker.acpokerapi.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type, Integer> {

    List<Type> findTypesByGameType(String gametype);

}
