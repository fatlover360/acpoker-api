package com.acpoker.acpokerapi.repository;

import com.acpoker.acpokerapi.entity.Cash;
import com.acpoker.acpokerapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CashRepository extends JpaRepository<Cash, Integer> {

    List<Cash> findByUser(User user);

    List<Cash> findByUserAndYearAndMonth(User user, Integer year, String month);
}
