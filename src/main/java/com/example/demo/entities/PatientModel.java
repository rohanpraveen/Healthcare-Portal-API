package com.example.demo.entities;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name="Patient_DTLS")
public class PatientModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Name should be at least 3 characters")
    private String name;

    @NotBlank(message = "City is required")
    @Size(max = 20, message = "City should be at most 20 characters")
    private String city;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Size(min = 10, message = "Phone number should be at least 10 numbers")
    private String phoneNumber;

    @NonNull
    private String symptom;
}
