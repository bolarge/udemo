package com.arc.udemo.rest;

import com.arc.udemo.domain.MailMessage;
import com.arc.udemo.domain.User;
import com.arc.udemo.domain.UserStatus;
import com.arc.udemo.exception.ResourceNotFoundException;
import com.arc.udemo.service.EmailService;
import com.arc.udemo.service.UDemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.arc.udemo.exception.error.ErrorDetail;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("api")
@Api(value = "users", tags = "User API")
public class UserRestController {

    private static Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    private UDemoService uDemoService;

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    @ApiOperation(value = "Retrieves given user", response = User.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "", response = User.class),
            @ApiResponse(code = 404, message = "Unable to find user", response = ErrorDetail.class)})
    public ResponseEntity<?> getUser(@PathVariable Integer userId) {
        Optional<User> user = this.uDemoService.findUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Retrieves all the users", response = User.class, responseContainer = "List")
    public ResponseEntity<Page<User>> getAllUsers(Pageable pageable) {
        Page<User> users = this.uDemoService.findAll(pageable);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "Creates a new User Request", notes = "The newly created user Id will be sent in the location response header", response = Void.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "User Application Successful", response = Void.class), @ApiResponse(code = 500, message = "Error creating User", response = ErrorDetail.class)})
    public ResponseEntity<?> createUser(@RequestBody @Valid User user) {

        User newUser = this.uDemoService.saveUser(user);
        try {
            MailMessage mailMessage = new MailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Application Confirmation");
            mailMessage.setText("<html><head></head><body><h3>Dear Applicant</h3><p>Please confirm your email <a href=\"http://localhost:8085/api/user/email/"+user.getEmail()+" \">to complete your application</a>.</p></body></html>");
            emailService.sendEmail(mailMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        responseHeaders.setLocation(newPollUri);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.PUT, produces = "application/json")
    @ApiOperation(value = "Updates given user", response = Void.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "", response = Void.class),
            @ApiResponse(code = 404, message = "Unable to find user", response = ErrorDetail.class)})
    public ResponseEntity<?> updateUser(@PathVariable Integer userId, @RequestBody User user) {
        verifyUser(userId);
        User updateUser = this.uDemoService.saveUser(user);
        return new ResponseEntity<>(updateUser, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE, produces = "application/json")
    @ApiOperation(value = "Deletes given user", response = Void.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "", response = Void.class),
            @ApiResponse(code = 404, message = "Unable to find user", response = ErrorDetail.class)})
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        verifyUser(userId);
        this.uDemoService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/*/email/{email}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Verify user request", response = Void.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "", response = Void.class), @ApiResponse(code = 404, message = "Unable to verify user", response = ErrorDetail.class)})
    public ResponseEntity<?> verifyUser(@PathVariable("email") String email) throws Exception {

        //verifyUser(userId);
        User verifiedUser = this.uDemoService.findUserByEmail(email);
        if(verifiedUser == null)
            throw new ResourceNotFoundException("User with id " + email + " not found");

        verifiedUser.setVerified(true);
        verifiedUser.setStatus(UserStatus.VERIFIED);
        this.uDemoService.saveUser(verifiedUser);

        MailMessage message = new MailMessage();
        message.setTo(verifiedUser.getEmail());
        message.setText("ApplicantVerification Complete");
        message.setSubject("<html><head></head><body><h3>Dear "+ verifiedUser.getFirstName() + ", </h3><br><p>Your email has been validated and your on-boarding  is on course. </p><br><p> You will " +
                "be notified with next set of actions to take.</p><br><br><p>uDemo Team</p></body></html>");
        emailService.sendEmail(message);
        return new ResponseEntity<User>(verifiedUser, HttpStatus.OK);
    }

    protected void verifyUser(Integer userId) throws ResourceNotFoundException {
        Optional<User> user = this.uDemoService.findUserById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User with id " + userId + " not found");
        }
    }

}
