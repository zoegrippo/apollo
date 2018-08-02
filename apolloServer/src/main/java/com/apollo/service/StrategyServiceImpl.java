package com.apollo.service;

import com.apollo.entities.Strategy;
import com.apollo.repos.StrategyRepository;
import com.apollo.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class StrategyServiceImpl implements StrategyService {
    @Autowired
    Validator validator;
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
        if (s.getId() == null) {
            s.setId(0);
        }
        if (validator.validateStrategy(s)) {
            if (dao.exists(s.getId())) {
                dao.save(s);
                return "Updated strategy";
            } else {
                return dao.save(s).getId().toString();
            }
        } else {
            return "Strategy parameters were invalid";
        }
    }

    // at the /strategy/{id} endpoint
    @Override
    public Strategy getById(int id) {
        if (validator.validateId(id)) {
            return dao.findOne(id);
        } else
            return null;
    }

    @Override
    public String deleteById(int id) {
        if (dao.exists(id) && validator.validateId(id)) {
            dao.delete(id);
            return "Deleted Strategy with ID number " + id;
        } else
            return "Unable to find Strategy with that ID or ID invalid";
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
