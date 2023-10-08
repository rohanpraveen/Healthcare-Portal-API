package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.DoctorModel;
import com.example.demo.entities.Enums.Locations;
import com.example.demo.entities.Enums.Speciality;
import com.example.demo.repositories.DoctorRepo;

@Service
public class DoctorService {
	@Autowired
	private DoctorRepo doctorRepo;

	public String insertDoctor(DoctorModel doc) {
		Locations doctorLocation = doc.getCity();
		Speciality doctorSpeciality = doc.getSpeciality();
		if ((doctorLocation == Locations.Delhi || doctorLocation == Locations.Faridabad
				|| doctorLocation == Locations.Noida)
				&& (doctorSpeciality == Speciality.Dermatology || doctorSpeciality == Speciality.ENT
						|| doctorSpeciality == Speciality.Gynecology || doctorSpeciality == Speciality.Orthopedic)) {
			return "Success:\n" + doctorRepo.save(doc).toString();
		}
		return "Failure";
	}

	public String updateDoctor(Integer id, DoctorModel updatedDoctor) {
		Optional<DoctorModel> existingDoctorOptional = doctorRepo.findById(id);
		Locations doctorLocation = updatedDoctor.getCity();
		Speciality doctorSpeciality = updatedDoctor.getSpeciality();

		if ((doctorLocation == Locations.Delhi || doctorLocation == Locations.Faridabad
				|| doctorLocation == Locations.Noida)
				&& (doctorSpeciality == Speciality.Dermatology || doctorSpeciality == Speciality.ENT
						|| doctorSpeciality == Speciality.Gynecology || doctorSpeciality == Speciality.Orthopedic)) {

			if (existingDoctorOptional.isPresent()) {
				DoctorModel existingDoctor = existingDoctorOptional.get();
				existingDoctor.setName(updatedDoctor.getName());
				existingDoctor.setCity(updatedDoctor.getCity());
				existingDoctor.setEmail(updatedDoctor.getEmail());
				existingDoctor.setPhoneNumber(updatedDoctor.getPhoneNumber());
				existingDoctor.setSpeciality(updatedDoctor.getSpeciality());
				DoctorModel savedDoctor = doctorRepo.save(existingDoctor);
				return "Success:\n" + savedDoctor.toString();
			}
		}
		return "Doctor not found";
	}

	public List<DoctorModel> getAllDoctorList() {
		return doctorRepo.findAll();
	}

	public String deleteDoctor(Integer id) {
		doctorRepo.deleteById(id);
		return "Success";
	}

}
