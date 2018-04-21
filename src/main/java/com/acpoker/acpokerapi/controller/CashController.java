package com.acpoker.acpokerapi.controller;

import com.acpoker.acpokerapi.entity.Cash;
import com.acpoker.acpokerapi.service.CashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/cash")
public class CashController {

    @Autowired
    private CashService cashService;

    @GetMapping("/find/{uid}")
    public List<Cash> findByUser(@PathVariable("uid") String uid) {
        return cashService.findByUser(uid);
    }

    @GetMapping("/{uid}/{year}/{month}")
    public List<Cash> findByUserYearAndMonth(@PathVariable("uid") String uid, @PathVariable("year") int year, @PathVariable("month") String month) {
        return cashService.findByUserYearAndMonth(uid, year, month);
    }

    @PostMapping("/add")
    public void createCash(@RequestBody Cash cash) {
        cashService.createCash(cash);
    }
}
