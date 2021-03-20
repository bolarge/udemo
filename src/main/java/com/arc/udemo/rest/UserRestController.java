package com.arc.udemo.rest;

import com.arc.udemo.domain.User;
import com.arc.udemo.service.UDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Collection;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("api/users")
public class UserRestController {

    @Autowired
    private UDemoService uDemoService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Collection<User>> getUsers() {
        Collection<User> users = this.uDemoService.findAllUser();
        if (users.isEmpty()) {
            return new ResponseEntity<Collection<User>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<User> addUser(@RequestBody @Valid User user, BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors() || user.getId() != null) {
            BindingErrorsResponse errors = new BindingErrorsResponse(user.getId());
            errors.addAllErrors(bindingResult);
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<User>(headers, HttpStatus.BAD_REQUEST);
        }
        this.uDemoService.saveUser(user);
        headers.setLocation(ucBuilder.path("/api/users/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<User>(user, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT, produces = "application/json")
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
        User currentUser = this.uDemoService.findUserById(userId);
        if (currentUser == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        currentUser.setTitle(user.getTitle());
        currentUser.setLastName(user.getLastName());
        currentUser.setMobilePhone(user.getMobilePhone());
        this.uDemoService.saveUser(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE, produces = "application/json")
    @Transactional
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") int userId) {
        User user = this.uDemoService.findUserById(userId);
        if (user == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        this.uDemoService.deleteUser(user);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
