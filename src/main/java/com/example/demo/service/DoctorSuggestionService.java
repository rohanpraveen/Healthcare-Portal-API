package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.DoctorModel;
import com.example.demo.entities.Enums.Locations;
import com.example.demo.entities.Enums.Speciality;
import com.example.demo.entities.PatientModel;
import com.example.demo.repositories.DoctorRepo;
import com.example.demo.repositories.PatientRepo;

import java.util.List;

@Service
public class DoctorSuggestionService {
    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private PatientRepo patientRepo;

    public Object suggestDoctors(Long patientId) {
        PatientModel patient = patientRepo.findById(patientId).orElse(null);

        if (patient == null) {
            return "Patient not found";
        }

        Locations patLocation = getPatientLocation(patient);

        if (patLocation == null) {
            return "We are still waiting to expand to your location";
        }

        Speciality specialist = matchSpecialist(patient.getSymptom());

        if (specialist == null) {
            return "There isn't any doctor present at your location for your symptom";
        }

        List<DoctorModel> suggestedDoctors = doctorRepo.findByCityAndSpeciality(patLocation, specialist);

        return suggestedDoctors;
    }

    private Locations getPatientLocation(PatientModel patient) {
        Locations patLocation = null;

        for (Locations location : Locations.values()) {
            if (location.name().equalsIgnoreCase(patient.getCity())) {
                patLocation = location;
                break;
            }
        }

        return patLocation;
    }

    private Speciality matchSpecialist(String keyword) {
        String lowercaseKeyword = keyword.toLowerCase();

        if (lowercaseKeyword.contains("arthritis") || lowercaseKeyword.contains("back pain") || lowercaseKeyword.contains("tissue injuries")) {
            return Speciality.Orthopedic;
        } else if (lowercaseKeyword.contains("dysmenorrhea")) {
            return Speciality.Gynecology;
        } else if (lowercaseKeyword.contains("skin infection") || lowercaseKeyword.contains("skin burn")) {
            return Speciality.Dermatology;
        } else if (lowercaseKeyword.contains("ear pain")) {
            return Speciality.ENT;
        } else {
            return null;
        }
    }
}
