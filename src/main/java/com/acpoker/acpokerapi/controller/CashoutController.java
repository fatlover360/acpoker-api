package com.acpoker.acpokerapi.controller;

import com.acpoker.acpokerapi.entity.Cashout;
import com.acpoker.acpokerapi.service.CashoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/cashout")
public class CashoutController {

    @Autowired
    private CashoutService cashoutService;

    @GetMapping("/{uid}")
    public List<Cashout> findAllByuser(@PathVariable("uid") String uid){
        return cashoutService.findByUser(uid);
    }

    @GetMapping("/{uid}/{month}/{year}")
    public List<Cashout> findAllByUserAndDate(@PathVariable("uid") String uid, @PathVariable("month") String month, @PathVariable("year") Integer year){
        return cashoutService.findByUserAndDate(uid,month,year);
    }

    @PostMapping("/add")
    public void addCashout(@RequestBody Cashout cashout){
        cashoutService.add(cashout);
    }

}
