package com.acpoker.acpokerapi.service;

import com.acpoker.acpokerapi.entity.Cash;
import com.acpoker.acpokerapi.repository.CashRepository;
import com.acpoker.acpokerapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CashService {

    @Autowired
    private CashRepository cashRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Cash> findByUser(String uid) {
        return cashRepository.findByUser(userRepository.findUserByUid(uid));
    }

    public void createCash(Cash cash) {
        long date = cash.getDate();
        cashRepository.save(cash);
    }

    public List<Cash> findByUserYearAndMonth(String uid, int year, String month) {
        return cashRepository.findByUserAndYearAndMonth(userRepository.findUserByUid(uid),year,month);
    }
}
