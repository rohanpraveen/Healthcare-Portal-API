package com.example.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.PatientModel;
import com.example.demo.repositories.PatientRepo;

@Service
public class PatientService {
    @Autowired
    private PatientRepo patientRepo;

    public PatientModel savePatient(PatientModel patient) {
        return patientRepo.save(patient);
    }
    
    public List<PatientModel> getAllPatients() {
        return patientRepo.findAll();
    }

    public PatientModel updatePatient(Integer id, PatientModel updatedPatient) {
        if (patientRepo.existsById(id)) {
            updatedPatient.setId(id);
            return patientRepo.save(updatedPatient);
        } else {
            return null;
        }
    }

    public void deletePatient(Long patientId) {
        patientRepo.deleteById(patientId);
    }

}
