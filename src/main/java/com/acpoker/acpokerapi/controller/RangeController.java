package com.acpoker.acpokerapi.controller;

import com.acpoker.acpokerapi.entity.RangeModel;
import com.acpoker.acpokerapi.service.RangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ranges")
public class RangeController {

    @Autowired
    private RangeService rangeService;

    @PostMapping("/model/add")
    public boolean addModel(@RequestBody List<RangeModel> range){
        return rangeService.addModel(range);
    }


}
