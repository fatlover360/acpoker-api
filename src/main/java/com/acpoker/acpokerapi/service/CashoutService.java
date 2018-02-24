package com.acpoker.acpokerapi.service;

import com.acpoker.acpokerapi.entity.Cashout;
import com.acpoker.acpokerapi.repository.CashoutRepository;
import com.acpoker.acpokerapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashoutService {

    @Autowired
    private CashoutRepository cashoutRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Cashout> findByUser(String uid) {
        return cashoutRepository.findByUser(userRepository.findUserByUid(uid));
    }

    public List<Cashout> findByUserAndDate(String uid, String month, Integer year) {
        return cashoutRepository.findByUserAndMonthAndYear(userRepository.findUserByUid(uid), month, year);
    }
}
