package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.DoctorSuggestionService;

@RestController
@RequestMapping("/api/suggest")
public class DoctorSuggestionController {

    @Autowired
    private DoctorSuggestionService doctorSuggestionService;

    @GetMapping("/suggest/{patientId}")
    public ResponseEntity<Object> suggestDoctors(@PathVariable Long patientId) {
        Object result = doctorSuggestionService.suggestDoctors(patientId);
        
        if (result == null) {
            return new ResponseEntity<>("Patient not found", HttpStatus.NOT_FOUND);
        } else if (result instanceof String) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
}