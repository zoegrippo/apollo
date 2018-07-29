package com.apollo.controllers;

import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
@CrossOrigin(origins="*") // allows requests from all domains
public class HealthController {

    private Logger logger = Logger.getLogger(this.getClass());

    @ApiOperation(value = "HealthCheck", nickname = "health")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> healthCheck() {
        logger.debug("Heart Beat");
        return new ResponseEntity<>("OK",HttpStatus.OK);
    }
}
