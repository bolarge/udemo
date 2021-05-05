package com.arc.udemo.v1.controller;

import com.arc.udemo.domain.billing.Band;
import com.arc.udemo.exception.error.ErrorDetail;
import com.arc.udemo.rest.dto.UsagePlanRequest;
import com.arc.udemo.service.UDemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController("usagePlanRestControllerV1")
@RequestMapping("/v1/")
@Api(value = "plans", tags = "Plan API")
public class UsagePlanRestController {

    private static Logger logger = LoggerFactory.getLogger(UsagePlanRestController.class);

    @Autowired
    private UDemoService uDemoService;

    @RequestMapping(value = "/plans", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "Creates a new Plan Request", notes = "The newly created plan Id will be sent in the location response header", response = Void.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Plan creation request successful", response = Void.class), @ApiResponse(code = 500, message = "Error creating plan", response = ErrorDetail.class)})
    public ResponseEntity<?> createUsagePlan(@RequestBody UsagePlanRequest usagePlan){
        Band newPlan = this.uDemoService.saveUsagePlan(usagePlan);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPlan.getId()).toUri();
        responseHeaders.setLocation(newPollUri);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
}
