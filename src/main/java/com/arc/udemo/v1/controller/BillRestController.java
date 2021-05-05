package com.arc.udemo.v1.controller;

import com.arc.udemo.domain.billing.Bill;
import com.arc.udemo.exception.error.ErrorDetail;
import com.arc.udemo.rest.dto.MonthlyBillRequest;
import com.arc.udemo.service.UDemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

@RestController("billRestControllerV1")
@RequestMapping("/v1/")
@Api(value = "bill", tags = "Bill API")
public class BillRestController {

    @Autowired
    private UDemoService uDemoService;

    @RequestMapping(value = "/bills", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "Creates a new Bill request", notes = "The newly created bill Id will be sent in the location response header", response = Void.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Bill creation request successful", response = Void.class), @ApiResponse(code = 500, message = "Error creating bill", response = ErrorDetail.class)})
    public ResponseEntity<?> generateMonthlyBill(@RequestBody MonthlyBillRequest monthlyBillRequest){
        Bill monthlyBill = this.uDemoService.generateUserMonthlyBill(monthlyBillRequest);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(monthlyBill.getId()).toUri();
        responseHeaders.setLocation(newPollUri);
        return new ResponseEntity<>(monthlyBill, responseHeaders, HttpStatus.CREATED);
    }
}
