package com.thoughtworks.basicquiz.controller;

import com.thoughtworks.basicquiz.model.Education;
import com.thoughtworks.basicquiz.service.EducationService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@Validated
public class EducationController {
    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @PostMapping("/{id}/educations")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEducation(@PathVariable long id, @RequestBody @Valid Education education) {
        educationService.addEducation(id, education);
    }

//    @GetMapping("/{id}/educations")
//    @ResponseStatus(HttpStatus.OK)
//    public List<Education> getEducationById(@PathVariable long id) {
//        return educationService.getEducationById(id);
//    }
}
