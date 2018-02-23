package com.acpoker.acpokerapi.controller;

import com.acpoker.acpokerapi.entity.Cash;
import com.acpoker.acpokerapi.repository.CashRepository;

import com.acpoker.acpokerapi.service.CashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/cash")
public class CashController {

    @Autowired
    private CashService cashService;

    @GetMapping("/{uid}")
    public List<Cash> findByUser(@Param("uid") String uid) {
        return cashService.findByUser(uid);
    }

    @GetMapping("/{uid}/{year}/{month}")
    public List<Cash> findByUserYearAndMonth(@PathVariable("uid") String uid, @PathVariable("year") int year, @PathVariable("month") String month) {
        return cashService.findByUserYearAndMonth(uid, year, month);
    }

    @PostMapping("/add/{uid}/{amount}/{year}/{month}/{day}")
    public void createCash(@PathVariable("uid") String uid, @PathVariable("amount") Double amount, @PathVariable("year") Integer year, @PathVariable("month") String month, @PathVariable("day") Integer day) {
        cashService.createCash(uid, amount, year, month, day);
    }
}
