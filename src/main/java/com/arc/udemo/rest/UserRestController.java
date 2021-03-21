package com.arc.udemo.rest;

import com.arc.udemo.domain.MailMessage;
import com.arc.udemo.domain.User;
import com.arc.udemo.rest.error.ErrorDetail;
import com.arc.udemo.service.EmailService;
import com.arc.udemo.service.UDemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("api/users")
@Api(value = "users", tags = "User API")
public class UserRestController {

    @Autowired
    private UDemoService uDemoService;

    @Autowired
    private EmailService emailService;

    @RequestMapping(value="/users/{userId}", method=RequestMethod.GET)
    @ApiOperation(value = "Retrieves given user", response=User.class)
    @ApiResponses(value = {@ApiResponse(code=200, message="", response=User.class),
            @ApiResponse(code=404, message="Unable to find user", response=ErrorDetail.class) } )
    public ResponseEntity<?> getPoll(@PathVariable Long userId) {
        Optional<User> user = this.uDemoService.findUserById(userId);
        return new ResponseEntity<> (user.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Retrieves all the users", response=User.class, responseContainer="List")
    public ResponseEntity<Page<User>> getAllUsers(Pageable pageable) {
        Page<User> users = this.uDemoService.findAll(pageable);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "Creates a new User Request", notes="The newly created user Id will be sent in the location response header",
            response = Void.class)
    @ApiResponses(value = {@ApiResponse(code=201, message="User Application Successful", response=Void.class),
            @ApiResponse(code=500, message="Error creating User", response= ErrorDetail.class) } )
    public ResponseEntity<User> createUser(@RequestBody @Valid User user, BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors() || user.getId() != null) {
            BindingErrorsResponse errors = new BindingErrorsResponse(user.getId());
            errors.addAllErrors(bindingResult);
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<User>(headers, HttpStatus.BAD_REQUEST);
        }

        try {
            //Send Application request confirmation
            MailMessage mailMessage = new MailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Application Request Confirmation");
            mailMessage.setText("Your application is received  and will be processed");
            emailService.sendEmail(mailMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.uDemoService.saveUser(user);
        headers.setLocation(ucBuilder.path("/api/users/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<User>(user, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT, produces = "application/json")
    @ApiOperation(value = "Updates given user", response=Void.class)
    @ApiResponses(value = {@ApiResponse(code=200, message="", response=Void.class),
            @ApiResponse(code=404, message="Unable to find user", response=ErrorDetail.class) } )
    public ResponseEntity<User> updateOwner(@PathVariable("userId") int userId, @RequestBody @Valid User user,
                                             BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
        boolean bodyIdMatchesPathId = user.getId() == null || userId == user.getId();
        if (bindingResult.hasErrors() || !bodyIdMatchesPathId) {
            BindingErrorsResponse errors = new BindingErrorsResponse(userId, user.getId());
            errors.addAllErrors(bindingResult);
            HttpHeaders headers = new HttpHeaders();
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<User>(headers, HttpStatus.BAD_REQUEST);
        }
        Optional<User> currentUser = this.uDemoService.findUserById(userId);
        if (currentUser == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        currentUser.get().setTitle(user.getTitle());
        currentUser.get().setLastName(user.getLastName());
        currentUser.get().setMobilePhone(user.getMobilePhone());
        this.uDemoService.saveUser(currentUser.get());
        return new ResponseEntity<User>(currentUser.get(), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE, produces = "application/json")
    @ApiOperation(value = "Deletes given user", response=Void.class)
    @ApiResponses(value = {@ApiResponse(code=200, message="", response=Void.class),
            @ApiResponse(code=404, message="Unable to find user", response=ErrorDetail.class) } )
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") int userId) {
        Optional<User> user = this.uDemoService.findUserById(userId);
        if (user == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        this.uDemoService.deleteUser(user.get());
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
