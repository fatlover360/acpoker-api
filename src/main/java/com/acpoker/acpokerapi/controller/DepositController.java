package com.acpoker.acpokerapi.controller;

import com.acpoker.acpokerapi.entity.Deposit;
import com.acpoker.acpokerapi.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/deposits")
public class DepositController {

    @Autowired
    private DepositService depositService;

    @GetMapping("/find/{year}/{month}")
    @CrossOrigin(value = "*")
    public List<Deposit> findByYearAndMonth(@PathVariable("month") String month, @PathVariable("year") Integer year) {
        return depositService.findByMonthAndYear(month, year);
    }

    @GetMapping("/find/{year}")
    @CrossOrigin(value = "*")
    public List<Deposit> findByYear(@PathVariable("year") Integer year) {
        return depositService.findByYear(year);
    }

    @PostMapping("/add")
    @CrossOrigin(value = "*")
    public void addDeposit(@RequestBody Deposit deposit){
        depositService.addDeposit(deposit);
    }

}
