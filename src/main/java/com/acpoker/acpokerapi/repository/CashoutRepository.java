package com.acpoker.acpokerapi.repository;

import com.acpoker.acpokerapi.entity.Cashout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashoutRepository extends JpaRepository<Cashout,Integer> {
}
