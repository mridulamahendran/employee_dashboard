package com.springboot.app.siemens.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.siemens.model.Employee;
import com.springboot.app.siemens.repository.EmployeeRepository;
import com.springboot.app.siemens.service.EmployeeInterface;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmpServiceImpl implements EmployeeInterface{
	
	@Autowired
	private EmployeeRepository er;

	@Override
	public List<Employee> getAllEmployees() {
		return er.findAll();
	}

	@Override
	public Employee addEmployee(Employee emp) {
		return er.save(emp);
	}

	@Override
	public Employee getEmployee(Long id) throws Exception {
		Optional<Employee> user = er.findById(id); 
		if(user.isPresent())
		{
			return user.get();
		}
		else
		{
			throw new Exception("No user found!");
		}
	}

	@Override
	public Employee updateEmployee(Employee emp) {
		Employee employee = er.findById(emp.getId()).get();
		employee.setFirstName(emp.getFirstName());
		employee.setLastName(emp.getLastName());
		employee.setGender(emp.getGender());
		employee.setDesignation(emp.getDesignation());
		employee.setDepartment(emp.getDepartment());
		employee.setYearsOfExperience(emp.getYearsOfExperience());
		Employee updatedEmployee = er.save(employee);
		return updatedEmployee;
	}

	@Override
	public void deleteEmployee(Long id) {
		er.deleteById(id);
	}

	@Override
	public List<Employee> getEmployeeForDepartment(String department) {
		List<Employee> employees = (List<Employee>) er.findByDepartment(department);
		return employees;
	}

	@Override
	public List<Employee> getEmployeeForExperience(Integer yearsOfExperience) {
		List<Employee> employees = (List<Employee>) er.findByExperience(yearsOfExperience);
		return employees;
	}

	@Override
	public List<Employee> getEmployeeForGender(String gender) {
		List<Employee> employees = (List<Employee>) er.findByGender(gender);
		return employees;
	}

	@Override
	public List<Employee> getEmployeeForDesignation(String designation) {
		List<Employee> employees = (List<Employee>) er.findByDesignation(designation);
		return employees;
	}

	@Override
	public List<Employee> getEmployeeForPrimarySkill(ArrayList<String> skills) {
		List<Employee> employees = er.findAll();
		List<Employee> resultEmployees = new ArrayList<>();
		
		for(Employee employee : employees) {
			ArrayList<String> empSkill = employee.getPrimarySkills();
			for(String skill : skills) {
				if(empSkill.contains(skill)) {
					resultEmployees.add(employee);
					break;
				}
			}
		}
		
		return resultEmployees;
	}

	@Override
	public List<Employee> getEmployeeForSecondarySkill(ArrayList<String> skills) {
		List<Employee> employees = er.findAll();
		List<Employee> resultEmployees = new ArrayList<>();
		
		for(Employee employee : employees) {
			ArrayList<String> empSkill = employee.getSecondarySkills();
			for(String skill : skills) {
				if(empSkill.contains(skill)) {
					resultEmployees.add(employee);
					break;
				}
			}
		}
		
		return resultEmployees;
	}

	@Override
	public HashMap<String, Integer> getEmployeeForExpDept(String department) {
		// TODO Auto-generated method stub
		List<Employee> employees = getEmployeeForDepartment(department);
		HashMap<String, Integer> hMap = new HashMap<>();
		hMap.put("0-3", 0);
		hMap.put("4-6", 0);
		hMap.put("7-10", 0);
		hMap.put("11-15", 0);
		hMap.put("15+", 0);
		
		for(Employee employee : employees) {
		    if(employee.getYearsOfExperience() >=0 ) {
		        int years = employee.getYearsOfExperience();
		        if(years >= 0 && years <= 3) {
		            hMap.put("0-3", hMap.get("0-3") + 1);
		        } else if(years >= 4 && years <= 6) {
		            hMap.put("4-6", hMap.get("4-6") + 1);
		        } else if(years >= 7 && years <= 10) {
		            hMap.put("7-10", hMap.get("7-10") + 1);
		        } else if(years >= 11 && years <= 15) {
		            hMap.put("11-15", hMap.get("11-15") + 1);
		        } else {
		            hMap.put("15+", hMap.get("15+") + 1);
		        }
		    }
		}
		
		return hMap;
		
	}

	@Override
	public HashMap<String, Integer> getEmployeeForGenDept(String department) {
		List<Employee> employees = getEmployeeForDepartment(department);
		HashMap<String, Integer> hMap = new HashMap<>();
		hMap.put("male", 0);
		hMap.put("female", 0);
		hMap.put("others", 0);
		
		for(Employee employee : employees) {
		    if(employee.getGender().equals("Male") ) {
		    	hMap.put("male", hMap.get("male") + 1);
		    }
		    else if(employee.getGender().equals("Female")) {
		    	hMap.put("female", hMap.get("female") + 1);
		    }
		    else {
		    	hMap.put("others", hMap.get("others") + 1);
		    }
		}
		
		return hMap;
	}

	@Override
	public HashMap<String, Integer> getEmployeeForDesignDept(String department) {
		List<Employee> employees = getEmployeeForDepartment(department);
		HashMap<String, Integer> hMap = new HashMap<>();
		hMap.put("Individual Contributors", 0);
		hMap.put("Technical Leads", 0);
		hMap.put("Reporting Managers", 0);
		hMap.put("Senior Managers", 0);
		
		for (Employee employee : employees) {
		    String designation = employee.getDesignation();
		    if (designation.equalsIgnoreCase("Individual Contributor")) {
		        hMap.put("Individual Contributors", hMap.get("Individual Contributors") + 1);
		    } else if (designation.equalsIgnoreCase("Technical Lead")) {
		        hMap.put("Technical Leads", hMap.get("Technical Leads") + 1);
		    } else if (designation.equalsIgnoreCase("Reporting Manager")) {
		        hMap.put("Reporting Managers", hMap.get("Reporting Managers") + 1);
		    } else if (designation.equalsIgnoreCase("Senior Manager")) {
		        hMap.put("Senior Managers", hMap.get("Senior Managers") + 1);
		    }
		}
		
		return hMap;
	}
	
	
}
