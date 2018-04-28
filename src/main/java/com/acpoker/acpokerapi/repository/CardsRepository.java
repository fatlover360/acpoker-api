package com.acpoker.acpokerapi.repository;

import com.acpoker.acpokerapi.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardsRepository extends JpaRepository<Cards, Integer> {
}
