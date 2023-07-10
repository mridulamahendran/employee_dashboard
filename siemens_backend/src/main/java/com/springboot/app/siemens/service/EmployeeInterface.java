package com.springboot.app.siemens.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.springboot.app.siemens.model.Employee;

public interface EmployeeInterface {
	public List<Employee> getAllEmployees();
	
	public Employee addEmployee(Employee emp);
	
	public Employee getEmployee(Long id) throws Exception;
	
	public Employee updateEmployee(Employee emp);
	
	public void deleteEmployee(Long id);
	
	public List<Employee> getEmployeeForDepartment(String department);
	
	public List<Employee> getEmployeeForExperience(Integer experience);
	
	public List<Employee> getEmployeeForGender(String gender);
	
	public List<Employee> getEmployeeForDesignation(String designation);
	
	public List<Employee> getEmployeeForPrimarySkill(ArrayList<String> skills);
	
	public List<Employee> getEmployeeForSecondarySkill(ArrayList<String> skills);
	
	public HashMap<String, Integer> getEmployeeForExpDept(String department);
	
	public HashMap<String, Integer> getEmployeeForGenDept(String department);
	
	public HashMap<String, Integer> getEmployeeForDesignDept(String department);

	
}
