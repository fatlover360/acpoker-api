package com.acpoker.acpokerapi.repository;

import com.acpoker.acpokerapi.entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<Deposit,Integer> {
}
