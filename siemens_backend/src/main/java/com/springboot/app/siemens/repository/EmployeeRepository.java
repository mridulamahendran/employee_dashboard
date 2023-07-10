package com.springboot.app.siemens.repository;

import java.util.ArrayList;

//import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.siemens.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	ArrayList<Employee> findByDepartment(String department);
	
	ArrayList<Employee> findByExperience(Integer experience);
	
	ArrayList<Employee> findByGender(String gender);
	
	ArrayList<Employee> findByDesignation(String designation);
}
