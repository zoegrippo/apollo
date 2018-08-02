package com.apollo.service;

import com.apollo.entities.Trade;
import com.apollo.repos.TradeRepository;
import com.apollo.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TradeServiceImpl implements TradeService {
    @Autowired
    private TradeRepository dao;

    @Autowired
    private Validator validator;

    @Override
    public Trade getTradeById(int id) {
        if (validator.validateId(id)) {
            return dao.getOne(id);
        } else
            return null;
    }

    // at the /trade endpoint
    @Override
    public Collection<Trade> getAll() {
        return dao.findAll();
    }

    // at the /trade/{id} endpoint
    @Override
    public Collection<Trade> getTradeByStrategy(int id) {
        if (validator.validateId(id)) {
            return dao.getAllByStrategy_Id(id);
        } else
            return null;
    }

    // at the /trade/{id}/{date} endpoint
    @Override
    public Collection<Trade> getTradeByStrategyAndTradeId(int sid, int tid) {
        if (validator.validateId(sid) && validator.validateId(tid)) {
            return dao.getAllByStrategy_IdAndTrade_Id(sid, tid);
        } else
            return null;
    }
}
