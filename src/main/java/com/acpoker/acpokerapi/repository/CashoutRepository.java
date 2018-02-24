package com.acpoker.acpokerapi.repository;

import com.acpoker.acpokerapi.entity.Cashout;
import com.acpoker.acpokerapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CashoutRepository extends JpaRepository<Cashout,Integer> {

    List<Cashout> findByUser(User user);

    List<Cashout> findByUserAndMonthAndYear(User user, String month, Integer year);
}
