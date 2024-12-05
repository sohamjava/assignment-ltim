package com.sohamsg.assignments.backend.reository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sohamsg.assignments.domain.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findByEmail(String email);
}
