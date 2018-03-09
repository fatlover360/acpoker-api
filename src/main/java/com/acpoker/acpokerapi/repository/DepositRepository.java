package com.acpoker.acpokerapi.repository;

import com.acpoker.acpokerapi.entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepositRepository extends JpaRepository<Deposit, Integer> {

    List<Deposit> findByMonthAndYear(String month, Integer year);

    List<Deposit> findByYear(Integer year);
}
