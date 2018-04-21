package com.acpoker.acpokerapi.service;

import com.acpoker.acpokerapi.entity.RangeModel;
import com.acpoker.acpokerapi.repository.RangeModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RangeService {

    @Autowired
    private RangeModelRepository rangeModelRepository;

    public boolean addModel(List<RangeModel> range) {
        try {
            rangeModelRepository.save(range);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
}
