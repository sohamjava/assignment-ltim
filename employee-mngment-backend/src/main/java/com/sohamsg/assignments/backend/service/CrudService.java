package com.sohamsg.assignments.backend.service;

import java.util.List;
import java.util.Optional;

import com.sohamsg.assignments.domain.entity.Department;
import com.sohamsg.assignments.domain.entity.Employee;

public interface CrudService {

	public List<Employee> findAllEmployees();
	public Employee findEmployeeById(Long id);
	public Employee saveEmployee (Employee employee);
	public Employee updateEmployee (Long id,Employee employee);
	public void deleteEmployeeById(Long id);
	
	
	public List<Department> findAllDepartments();
	public Department findDepartmentById(Integer id);
	public Department saveDepartment(Department department);
	public Department updateDepartment(Integer id,Department department);
	public void deleteDepartmentById(Integer id);
	
	
}
