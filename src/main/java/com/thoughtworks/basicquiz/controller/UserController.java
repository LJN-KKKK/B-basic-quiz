package com.thoughtworks.basicquiz.controller;

import com.thoughtworks.basicquiz.model.Education;
import com.thoughtworks.basicquiz.model.User;
import com.thoughtworks.basicquiz.service.EducationService;
import com.thoughtworks.basicquiz.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final EducationService educationService;

    public UserController(UserService userService, EducationService educationService) {
        this.userService = userService;
        this.educationService = educationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("/{id}/educations")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEducation(@PathVariable long id, @RequestBody Education education) {
        educationService.addEducation(id, education);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/{id}/educations")
    @ResponseStatus(HttpStatus.OK)
    public List<Education> getEducationById(@PathVariable long id) {
        return educationService.getEducationById(id);
    }
}
