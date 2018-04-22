package com.acpoker.acpokerapi.repository;

import com.acpoker.acpokerapi.entity.RangeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RangeModelRepository extends JpaRepository<RangeModel,Integer> {

    List<RangeModel> findByTypeAndPositionAndBlindAndGameType(String type, String position,String blind, String gametype);

    List<RangeModel> findByType(String type);

}
