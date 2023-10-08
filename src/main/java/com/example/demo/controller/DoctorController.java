package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.DoctorModel;
import com.example.demo.service.DoctorService;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public ResponseEntity<String> insertDoctor(@RequestBody DoctorModel doctor) {
        String result = doctorService.insertDoctor(doctor);
        return ResponseEntity.status(result.contains("Success") ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDoctor(@PathVariable Integer id, @RequestBody DoctorModel updatedDoctor) {
        String result = doctorService.updateDoctor(id, updatedDoctor);
        return ResponseEntity.status(result.contains("Success") ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(result);
    }

    @GetMapping
    public List<DoctorModel> getAllDoctors() {
        return doctorService.getAllDoctorList();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Integer id) {
        String result = doctorService.deleteDoctor(id);
        return ResponseEntity.status(result.equals("Success") ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(result);
    }
}
