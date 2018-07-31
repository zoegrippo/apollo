package com.apollo.repos;
import com.apollo.entities.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer> {
}