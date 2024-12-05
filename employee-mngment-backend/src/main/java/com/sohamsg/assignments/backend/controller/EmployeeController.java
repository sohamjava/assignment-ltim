package com.sohamsg.assignments.backend.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sohamsg.assignments.backend.service.CrudService;
import com.sohamsg.assignments.domain.entity.Employee;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/employees")
@Validated
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {

	private final CrudService crudService;

	@GetMapping
	public List<Employee> findAll() {
		return crudService.findAllEmployees();
	}

	@GetMapping("/{id}")
	public Employee findById(@PathVariable("id") Long id) {
		return crudService.findEmployeeById(id);
	}

	@PostMapping
	public Employee save(@RequestBody @Valid Employee employee) {
		return crudService.saveEmployee(employee);
	}

	@PatchMapping("/{id}")
	public Employee update(@PathVariable("id") Long id, @RequestBody @Valid Employee employee) {

		return crudService.updateEmployee(id, employee);
	}

	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable("id") Integer id) {
		crudService.deleteEmployeeById((long) id);
	}
}
