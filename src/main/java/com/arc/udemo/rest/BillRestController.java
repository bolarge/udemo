package com.arc.udemo.rest;

import com.arc.udemo.domain.billing.Bill;
import com.arc.udemo.rest.dto.MonthlyBillRequest;
import com.arc.udemo.service.UDemoService;
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

@RestController
@RequestMapping("/api/")
public class BillRestController {

    @Autowired
    private UDemoService uDemoService;

    @RequestMapping(value = "/bills", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> generateMonthlyBill(@RequestBody MonthlyBillRequest monthlyBillRequest){
        Bill monthlyBill = this.uDemoService.generateUserMonthlyBill(monthlyBillRequest);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(monthlyBill.getId()).toUri();
        responseHeaders.setLocation(newPollUri);
        return new ResponseEntity<>(monthlyBill, responseHeaders, HttpStatus.CREATED);
    }
}
