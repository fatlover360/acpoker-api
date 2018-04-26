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
            rangeModelRepository.saveAll(models);
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
            rangeModelRepository.saveAll(ranges);
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

    public List<RangeModel> getRangesByTypeAndPosition(String type, String position, String blind, String gametype) {

        List<RangeModel> defaultList = rangeModelRepository.findByType(DEFAULT);

        List<RangeModel> withPositionsAndTypeList = rangeModelRepository.findByTypeAndPositionAndBlindAndGameType(type, position, blind, gametype);

        defaultList.forEach(range -> {
            withPositionsAndTypeList.forEach( withPos -> {
                if(range.getValue().equals(withPos.getValue()) && range.getKind().equals(withPos.getKind())){
                    range.setType(withPos.getType());
                    range.setPosition(withPos.getPosition());
                    range.setPercentage(withPos.getPercentage());
                    range.setBlind(withPos.getBlind());
                    range.setGameType(withPos.getGameType());
                    range.setColor(withPos.getColor());
                }
            });
        });

        return defaultList;
    }

    public boolean remove(String type) {
        List<RangeModel> list = rangeModelRepository.findByType(type);
        rangeModelRepository.deleteAll(list);

        typeRepository.findAll().forEach(s -> {
            if(s.getType().equals(type)) {
                typeRepository.delete(s);
            }
        });

        return true;
    }
}
