package com.acpoker.acpokerapi.service;

import com.acpoker.acpokerapi.entity.RangeModel;
import com.acpoker.acpokerapi.entity.Type;
import com.acpoker.acpokerapi.repository.RangeModelRepository;
import com.acpoker.acpokerapi.repository.TypeRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RangeService {

    private static final String DEFAULT = "DEFAULT";
    private static Logger LOGGER = Logger.getLogger(RangeService.class);

    @Autowired
    private RangeModelRepository rangeModelRepository;

    @Autowired
    private TypeRepository typeRepository;

    public boolean addModel(List<RangeModel> models) {
        try {
            LOGGER.info("ADDING DEFAULT RANGES");
            models.forEach(r-> r.setType(DEFAULT));
            rangeModelRepository.save(models);
            return true;
        }catch (Exception e){
            LOGGER.error("ERROR WHILE ADDING DEFAULT RANGES", e);
            return false;
        }

    }

    public List<RangeModel> getDefaultRanges() {
        return rangeModelRepository.findByType(DEFAULT);
    }

    public boolean addRange(List<RangeModel> ranges) {
        try {
            LOGGER.info("ADDING RANGES");
            rangeModelRepository.save(ranges);
            return true;
        }catch (Exception e){
            LOGGER.error("ERROR WHILE ADDING RANGES", e);
            return false;
        }
    }

    public boolean addType(Type type) {
        try{
            typeRepository.save(type);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Type> getTypes() {
        return typeRepository.findAll();
    }

    public List<RangeModel> getRangesByTypeAndPosition(String type, String position) {
        return rangeModelRepository.findByTypeAndPosition(type, position);
    }
}
