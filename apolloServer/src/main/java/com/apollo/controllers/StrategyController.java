package com.apollo.controllers;

import com.apollo.entities.Strategy;
import com.apollo.service.StrategyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/api/strategies")
@CrossOrigin // allows requests from all domains
public class StrategyController {
    @Autowired
    private StrategyService service;

    @ApiOperation(value = "findActive", nickname = "findActive")
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Strategy> findActive() {
        return service.getActive();
    }
}
