package com.example.demo.entities;

import lombok.Data;

@Data
public class Enums {
	public enum Locations{
		Delhi, 
		Noida, 
		Faridabad;
	}
	public enum Speciality{
		Orthopedic, 
		Gynecology, 
		Dermatology,
		ENT;
	}
}
