package com.example.demo.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.PatientModel;

public interface PatientRepo extends JpaRepository<PatientModel, Serializable> {
}