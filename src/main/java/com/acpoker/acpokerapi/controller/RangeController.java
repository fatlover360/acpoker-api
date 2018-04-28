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
    @CrossOrigin(value = "*")
    public boolean addModel(@RequestBody List<RangeModel> range){
        return rangeService.addModel(range);
    }

    @PostMapping("/add")
    @CrossOrigin(value = "*")
    public boolean addRanges(@RequestBody List<RangeModel> range){
        return rangeService.addRange(range);
    }

    @PostMapping("/type/add")
    @CrossOrigin(value = "*")
    public boolean addType(@RequestBody Type type){
        return rangeService.addType(type);
    }

    @GetMapping("/find/{type}/{position}/{blind}/{gametype}")
    @CrossOrigin(value = "*")
    public List<RangeModel> getRangesByType(@PathVariable("type") String type, @PathVariable("position") String position, @PathVariable("blind") String blind, @PathVariable("gametype") String gametype){
        return rangeService.getRangesByTypeAndPosition(type, position, blind, gametype);
    }

    @DeleteMapping("/delete/{type}")
    @CrossOrigin(value = "*")
    public boolean delete(@PathVariable("type") String type) {
        return rangeService.remove(type);
    }

    @GetMapping("/types/all/{gametype}")
    @CrossOrigin(value = "*")
    public List<Type> getAllTypes(@PathVariable String gametype){
        return rangeService.getTypes(gametype);
    }

    @GetMapping("/default")
    @CrossOrigin(value = "*")
    public List<RangeModel> getDefault(){
        return rangeService.getDefaultRanges();
    }


}
