package com.apollo.controllers;

import com.apollo.entities.Trade;
import com.apollo.service.TradeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin // allows requests from all domains
@RequestMapping("/trade")
public class TradeController {
    @Autowired
    private TradeService service;

    @ApiOperation(value = "getAll", nickname = "getAll")
    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "createNewTrade", nickname = "createNewTrade")
    @PostMapping("/")
    public ResponseEntity<?> createNewStrategy(@RequestBody Trade t) {
        try {
            return new ResponseEntity<>(service.createOrUpdate(t), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "getByStrategyId", nickname = "getByStrategyId")
    @GetMapping("/{id}")
    public ResponseEntity<?> getByStrategyId(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(service.getTradeByStrategy(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}