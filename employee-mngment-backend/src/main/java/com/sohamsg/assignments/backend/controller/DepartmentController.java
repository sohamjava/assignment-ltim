package com.sohamsg.assignments.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sohamsg.assignments.backend.service.CrudService;
import com.sohamsg.assignments.domain.entity.Department;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/departments")

@Slf4j
@RequiredArgsConstructor
public class DepartmentController {
	private final CrudService crudService;

	@GetMapping
	public List<Department> findAll() {
		return crudService.findAllDepartments();
	}

	@GetMapping("/{id}")
	public Department findById(@PathVariable("id") int id) {

		return crudService.findDepartmentById(id);
	}

	@PostMapping
	public Department save(@RequestBody Department department) {
		return crudService.saveDepartment(department);
	}

	@PatchMapping("/{id}")
	public Department update(@PathVariable("id") int id, @RequestBody Department department) {

		return crudService.updateDepartment(id, department);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") int id) {

		crudService.deleteDepartmentById(id);
	}
}
