package com.arc.udemo.rest;

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
import java.util.Collection;

@RestController
@RequestMapping("/api/")
@Api(value = "bills", tags = "Bill API")
public class BillRestController {

    @Autowired
    private UDemoService uDemoService;

    @RequestMapping(value = "/bills/single", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "Creates a new  Customer Bill", notes = "The newly created customer bill Id will be sent in the location response header", response = Void.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Bill creation successful", response = Void.class), @ApiResponse(code = 500, message = "Error creating bill", response = ErrorDetail.class)})
    public ResponseEntity<?> generateCustomerMonthlyBill(@RequestBody MonthlyBillRequest monthlyBillRequest) throws Exception {
        Bill monthlyBill = this.uDemoService.generateUserMonthlyBill(monthlyBillRequest);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(monthlyBill.getId()).toUri();
        responseHeaders.setLocation(newPollUri);
        return new ResponseEntity<>(monthlyBill, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/bills/batch", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "Generates Customers monthly bill", notes = "Generates monthly bill for all customers ", responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Bills generation request successful", response = Bill.class), @ApiResponse(code = 500, message = "Error creating bill", response = ErrorDetail.class)})
    public ResponseEntity<Collection<Bill>> generateCustomersMonthlyBill(@RequestBody MonthlyBillRequest monthlyBillRequest) throws Exception {
        Collection<Bill> customersMonthlyBill = this.uDemoService.generateMonthlyBill(monthlyBillRequest);
        return new ResponseEntity<>(customersMonthlyBill, HttpStatus.OK);
    }
}
