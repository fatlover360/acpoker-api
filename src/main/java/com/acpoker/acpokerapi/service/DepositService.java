package com.acpoker.acpokerapi.service;

import com.acpoker.acpokerapi.entity.Deposit;
import com.acpoker.acpokerapi.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositService {

    @Autowired
    private DepositRepository depositRepository;

    public List<Deposit> findByMonthAndYear(String month, Integer year) {
        return depositRepository.findByMonthAndYear(month, year);
    }

    public List<Deposit> findByYear(Integer year) {
        return depositRepository.findByYear(year);
    }

    public void addDeposit(Deposit deposit) {
        depositRepository.save(deposit);
    }
}
