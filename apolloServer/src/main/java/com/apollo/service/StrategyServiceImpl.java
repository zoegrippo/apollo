package com.apollo.service;

import com.apollo.entities.Strategy;
import com.apollo.repos.StrategyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class StrategyServiceImpl implements StrategyService {
    @Autowired
    private StrategyRepository dao;

    //only at the Service level
    @Override
    public Collection<Strategy> getActive() {
        return dao.findStrategiesByOnoffIs(true);
    }

    @Override
    public Collection<String> getTickers() {
        return dao.findStockByOnoffIsTrue();
    }

    // at the /strategy endpoint
    @Override
    public Collection<Strategy> getAll() {
        return dao.findAll();
    }

    @Override
    public String createOrUpdate(Strategy s) {
        String result;
        if (dao.exists(s.getId())) {
            result = "Updated strategy";
        } else {
            result = "Saved new strategy";
        }
        dao.save(s);
        return result;
    }

    // at the /strategy/{id} endpoint
    @Override
    public Strategy getById(int id) {
        return dao.findOne(id);
    }

    @Override
    public String deleteById(int id) {
        if (dao.exists(id)) {
            dao.delete(id);
            return "Deleted Strategy with ID number " + id;
        } else
            return "Unable to find Strategy with that ID";
    }

    // at the /strategy/start endpoint
    @Override
    public String startById(ArrayList<Integer> ids) {
        ArrayList<Integer> actual = new ArrayList<>();
        for (int i : ids) {
            if (dao.exists(i)) {
                actual.add(i);
                Strategy s = dao.getOne(i);
                s.setOnoff(true); // set to on option
                dao.save(s);
            }
        }
        if (actual.equals(ids)) {
            return "Started all strategies in list";
        } else {
            return "Unable to start all strategies in list";
        }
    }

    // at the /strategy/stop endpoint
    @Override
    public String stopById(ArrayList<Integer> ids) {
        ArrayList<Integer> actual = new ArrayList<>();
        for (int i : ids) {
            if (dao.exists(i)) {
                actual.add(i);
                Strategy s = dao.getOne(i);
                s.setOnoff(false); // set to on option
                dao.save(s);
            }
        }
        if (actual.equals(ids)) {
            return "Stopped all strategies in list";
        } else {
            return "Unable to stop all strategies in list";
        }
    }
}
