package com.apollo.services.db;

import com.apollo.entities.Strategy;
import com.apollo.repos.StrategyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StrategyServiceImpl implements StrategyService {
    @Autowired
    private StrategyRepository dao;

    @Override
    public Collection<Strategy> getActive() {
        return dao.findStrategiesByOnoffIs(true);
    }

    @Override
    public Collection<String> getTickers() {
        return dao.findStockByOnoffIsTrue();
    }

    @Override
    public String update(Strategy s) {
        if (s.getId() == null) {
            s.setId(0);
        }
        if (dao.exists(s.getId())) {
            dao.save(s);
            return "Updated trade";
        } else {
            return dao.save(s).getId().toString();
        }
    }

}
