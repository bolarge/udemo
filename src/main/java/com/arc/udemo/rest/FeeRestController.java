package com.arc.udemo.rest;

import com.arc.udemo.domain.billing.Fee;
import com.arc.udemo.exception.error.ErrorDetail;
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

@RestController
@RequestMapping("/api/")
@Api(value = "fees", tags = "Fees API")
public class FeeRestController {

    @Autowired
    private UDemoService uDemoService;

    @RequestMapping(value = "/fees", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "Creates a new Fee request", notes = "The newly created fee Id will be sent in the location response header", response = Void.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Fee creation request successful", response = Void.class), @ApiResponse(code = 500, message = "Error creating fee", response = ErrorDetail.class)})
    public ResponseEntity<?> createFee(@RequestBody Fee fee){
        Fee newFee = this.uDemoService.saveFee(fee);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newFee.getId()).toUri();
        responseHeaders.setLocation(newPollUri);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
}
