package com.acpoker.acpokerapi.controller;

import com.acpoker.acpokerapi.entity.RangeModel;
import com.acpoker.acpokerapi.entity.Type;
import com.acpoker.acpokerapi.service.RangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/range")
public class RangeController {

    @Autowired
    private RangeService rangeService;

    @PostMapping("/model/add")
    public boolean addModel(@RequestBody List<RangeModel> range){
        return rangeService.addModel(range);
    }

    @PostMapping("/add")
    public boolean addRanges(@RequestBody List<RangeModel> range){
        return rangeService.addRange(range);
    }

    @PostMapping("/type/add")
    public boolean addType(@RequestBody Type type){
        return rangeService.addType(type);
    }

    @GetMapping("/{type}/{position}")
    public List<RangeModel> getRangesByType(@PathVariable("type") String type, @PathVariable("position") String position){
        return rangeService.getRangesByTypeAndPosition(type, position);
    }

    @GetMapping("/types/all")
    public List<Type> getAllTypes(){
        return rangeService.getTypes();
    }

    @GetMapping("/default")
    public List<RangeModel> getDefault(){
        return rangeService.getDefaultRanges();
    }


}
