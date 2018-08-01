package com.apollo.controllers;

import com.apollo.entities.Strategy;
import com.apollo.service.StrategyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin // allows requests from all domains
public class StrategyController {
    @Autowired
    private StrategyService service;

    @ApiOperation(value = "getAll", nickname = "getAll")
    @RequestMapping(value = "/strategy", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "createNewStrategy", nickname = "createNewStrategy")
    @RequestMapping(value = "/strategy", method = RequestMethod.POST)
    public ResponseEntity<?> createNewStrategy(@RequestBody Strategy s) {
        try {
            return new ResponseEntity<>(service.createOrUpdate(s), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "getStrategyById", nickname = "getStrategyById")
    @RequestMapping(value = "/strategy/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getStrategyById(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "deleteStrategyById", nickname = "deleteStrategyById")
    @RequestMapping(value = "/strategy/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteStrategyById(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(service.deleteById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "startStrategyById", nickname = "startStrategyById")
    @RequestMapping(value = "/strategy/start", method = RequestMethod.POST)
    public ResponseEntity<?> startStrategyById(@RequestBody ArrayList<Integer> ids) {
        try {
            return new ResponseEntity<>(service.startById(ids), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "stopStrategyById", nickname = "stopStrategyById")
    @RequestMapping(value = "/strategy/stop", method = RequestMethod.POST)
    public ResponseEntity<?> stopStrategyById(@RequestBody ArrayList<Integer> ids) {
        try {
            return new ResponseEntity<>(service.stopById(ids), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}