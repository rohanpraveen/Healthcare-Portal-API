package com.example.demo.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.DoctorModel;
import com.example.demo.entities.Enums.Locations;
import com.example.demo.entities.Enums.Speciality;

public interface DoctorRepo extends JpaRepository<DoctorModel, Serializable> {
	
	@Query("SELECT d FROM DoctorModel d WHERE d.city = :city AND d.speciality = :speciality")
	List<DoctorModel> findByCityAndSpeciality(@Param("city") Locations city, @Param("speciality") Speciality speciality);
}
