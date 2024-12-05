package com.sohamsg.assignments.backend.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.sohamsg.assignments.backend.reository.DepartmentRepository;
import com.sohamsg.assignments.backend.reository.EmployeeRepository;
import com.sohamsg.assignments.backend.service.CrudService;
import com.sohamsg.assignments.domain.entity.Department;
import com.sohamsg.assignments.domain.entity.Employee;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CrudServiceImpl implements CrudService {
	private final DepartmentRepository departmentRepository;
	private final EmployeeRepository employeeRepository;

	@Override
	public List<Employee> findAllEmployees() {

		return employeeRepository.findAll();
	}

	public Employee findEmployeeById(Long id) {
		Optional<Employee> employeeById = employeeRepository.findById(id);
		if (employeeById.isEmpty()) {
			throw new NoSuchElementException("Employee id not found: " + id);
		}
		return employeeById.get();

	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public Employee saveEmployee(Employee employee) {

		return employeeRepository.save(employee);
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public Employee updateEmployee(Long id, Employee employee) {
		var emp=findEmployeeById(id);
		employee.setId(id);

		return saveEmployee(employee);
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public void deleteEmployeeById(Long id) {
		findEmployeeById(id);
		employeeRepository.deleteById(id);

	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public Department saveDepartment(Department department) {
		return departmentRepository.save(department);
	}

	@Override
	public List<Department> findAllDepartments() {

		return departmentRepository.findAll();
	}

	@Override
	public Department findDepartmentById(Integer id) {
		Optional<Department> departmentById = departmentRepository.findById(id);
		if (departmentById.isEmpty()) {
			throw new NoSuchElementException("Department id not found: " + id);
		}
		return departmentById.get();
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public void deleteDepartmentById(Integer id) {
		Optional<Department> departmentById = departmentRepository.findById(id);
		if (departmentById.isEmpty()) {
			throw new NoSuchElementException("Department id not found: " + id);
		}
		departmentRepository.deleteById(id);
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@Override
	public Department updateDepartment(Integer id, Department dept) {
		Optional<Department> departmentById = departmentRepository.findById(id);
		if (departmentById.isEmpty()) {
			throw new NoSuchElementException("Department id not found: " + id);
		}
		dept.setId(id);

		return saveDepartment(dept);
	}

}
