package com.sohamsg.assignments.backend.reository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sohamsg.assignments.domain.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	public Optional<Department> findById(Integer id);
	public List<Department> findByName(String name);
}
