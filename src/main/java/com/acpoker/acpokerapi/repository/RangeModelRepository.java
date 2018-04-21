package com.acpoker.acpokerapi.repository;

import com.acpoker.acpokerapi.entity.RangeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RangeModelRepository extends JpaRepository<RangeModel,Integer> {

    List<RangeModel> findByTypeAndPosition(String type, String position);

    List<RangeModel> findByType(String type);

}
