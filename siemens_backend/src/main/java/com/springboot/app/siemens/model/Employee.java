package com.springboot.app.siemens.model;

import java.util.ArrayList;

//import org.springframework.stereotype.Repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="Employee")
public class Employee {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "department")
	private String department;
	
	@Column(name = "designation")
	private String designation;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "years_of_experience")
	private Integer experience;
	
	@Column(name = "primary_skills")
	private ArrayList<String> primarySkills;
	
	@Column(name = "secondary_skills")
	private ArrayList<String> secondarySkills;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getYearsOfExperience() {
		return experience;
	}

	public void setYearsOfExperience(Integer experience) {
		this.experience = experience;
	}

	public ArrayList<String> getPrimarySkills() {
		return primarySkills;
	}

	public void setPrimarySkills(ArrayList<String> primarySkills) {
		this.primarySkills = primarySkills;
	}

	public ArrayList<String> getSecondarySkills() {
		return secondarySkills;
	}

	public void setSecondarySkills(ArrayList<String> secondarySkills) {
		this.secondarySkills = secondarySkills;
	}
	
	
}
