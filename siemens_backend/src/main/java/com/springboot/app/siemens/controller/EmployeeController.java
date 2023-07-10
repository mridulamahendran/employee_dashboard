package com.springboot.app.siemens.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.siemens.model.Employee;
import com.springboot.app.siemens.service.EmployeeInterface;
//import com.springboot.app.siemens.service.impl.EmpServiceImpl;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@AllArgsConstructor
@RequestMapping("api/employee")
public class EmployeeController {
	@Autowired
	private EmployeeInterface es;
	
    @GetMapping("{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") Long id) throws Exception{
    	try {
	    	Employee employee = es.getEmployee(id);
	    	if(employee != null) {
	    		return new ResponseEntity<>(employee, HttpStatus.OK);
	    	}
    	}
    	catch (Exception e) {
		}
        return new ResponseEntity<>("Employee not found!", HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<?> getAllEmployees(){
        List<Employee> employees = es.getAllEmployees();
        if(employees != null && !employees.isEmpty()) {
            return new ResponseEntity<>(employees, HttpStatus.OK);
        }
        return new ResponseEntity<>("No employees found!", HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("{identifier}/{key}")
    public ResponseEntity<?> getEmployeeCriteria(@PathVariable("identifier") String identifier, @PathVariable("key") String key) throws UnsupportedEncodingException{
    	List<Employee> employees = null;
    	if(identifier.equals("gender")) {
    		employees = es.getEmployeeForGender(key);
    	}
    	else if(identifier.equals("designation")) {
    		employees = es.getEmployeeForDesignation(key);
    	}
    	else if(identifier.equals("department")) {
    		employees = es.getEmployeeForDepartment(key);
    	}
    	else if(identifier.equals("experience")) {
    		Integer experienceInteger = Integer.parseInt(key);
    		employees = es.getEmployeeForExperience(experienceInteger);
    	}
    	else if(identifier.equals("exp")) {
    		HashMap<String, Integer> hMap = es.getEmployeeForExpDept(key);
    		return new ResponseEntity<>(hMap, HttpStatus.OK);
    	}
    	else if(identifier.equals("gen")) {
    		HashMap<String, Integer> hMap = es.getEmployeeForGenDept(key);
    		return new ResponseEntity<>(hMap, HttpStatus.OK);
    	}
    	else if(identifier.equals("design")) {
    		HashMap<String, Integer> hMap = es.getEmployeeForDesignDept(key);
    		return new ResponseEntity<>(hMap, HttpStatus.OK);
    	}
    	else if(identifier.equals("pskills")) {
    		String skillString = URLDecoder.decode(key, "UTF-8");
    		String[] skillArray = skillString.replaceAll("[\\[\\]\\s'\"]", "").split(",");
    	    ArrayList<String> skills = new ArrayList<>(Arrays.asList(skillArray));
    		employees = es.getEmployeeForPrimarySkill(skills);
    	}
    	else if(identifier.equals("sskills")) {
    		String skillString = URLDecoder.decode(key, "UTF-8");
    		String[] skillArray = skillString.replaceAll("[\\[\\]\\s'\"]", "").split(",");
    	    ArrayList<String> skills = new ArrayList<>(Arrays.asList(skillArray));
    		employees = es.getEmployeeForSecondarySkill(skills);
    	}
    	if(employees != null && !employees.isEmpty()) {
    		return new ResponseEntity<>(employees, HttpStatus.OK);
    	}
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
    	Employee savedEmployee = es.addEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
    
    @PostMapping("multiple")
    public ResponseEntity<List<Employee>> addMultipleEmployee(@RequestBody List<Employee> employees){
    	List<Employee> savedEmployees = new ArrayList<>();
    	for(Employee employee : employees) {
    		savedEmployees.add(es.addEmployee(employee));
    	}
        return new ResponseEntity<>(savedEmployees, HttpStatus.CREATED);
    }
    
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee){
    	employee.setId(id);
    	Employee updatedEmployee = es.updateEmployee(employee);
		return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
	}
    
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
        es.deleteEmployee(id);
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "User successfully deleted!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
