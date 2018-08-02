package com.apollo.services.db;

import com.apollo.entities.Trade;
import com.apollo.repos.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TradeServiceImpl implements TradeService {
    @Autowired
    private TradeRepository dao;

    @Override
    public Trade getTradeById(int id) {
        return dao.getOne(id);
    }

    // at the /trade endpoint
    @Override
    public Collection<Trade> getAll() {
        return dao.findAll();
    }

    @Override
    public String createOrUpdate(Trade t) {
        if (t.getId() == null) {
            t.setId(0);
        }
        if (dao.exists(t.getId())) {
            dao.save(t);
            return "Updated trade";
        } else {
            return dao.save(t).getId().toString();
        }
    }

    // at the /trade/{id} endpoint
    @Override
    public Collection<Trade> getTradeByStrategy(int id) {
        return dao.getAllByStrategy_Id(id);
    }

    // at the /trade/{id}/{id} endpoint
    @Override
    public Collection<Trade> getTradeByStrategyAndTradeId(int sid, int tid) {
        return dao.getAllByStrategy_IdAndTrade_Id(sid, tid);
    }
}