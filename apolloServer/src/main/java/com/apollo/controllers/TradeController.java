package com.apollo.controllers;

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
    @GetMapping()
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "getTradesByStrategyId", nickname = "getTradesByStrategyId")
    @GetMapping("/{id}")
    public ResponseEntity<?> getTradesByStrategyId(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(service.getTradeByStrategy(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "getLatestTrades", nickname = "getLatestTrades")
    @GetMapping("/{sid}/{tid}")
    public ResponseEntity<?> getLatestTrades(@PathVariable("sid") int sid, @PathVariable("tid") int tid) {
        try {
            return new ResponseEntity<>(service.getTradeByStrategyaAndTradeId(sid, tid), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}