package com.arc.udemo.rest;

import com.arc.udemo.domain.billing.Fee;
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
public class FeeRestController {

    @Autowired
    private UDemoService uDemoService;

    @RequestMapping(value = "/fees", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> createFee(@RequestBody Fee fee){
        Fee newFee = this.uDemoService.saveFee(fee);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newFee.getId()).toUri();
        responseHeaders.setLocation(newPollUri);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
}
