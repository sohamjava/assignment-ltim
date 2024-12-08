package com.sohamsg.assignments.backend.service.impl;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.sohamsg.assignments.backend.reository.DepartmentRepository;
import com.sohamsg.assignments.backend.reository.EmployeeRepository;
import com.sohamsg.assignments.backend.service.CrudService;
import com.sohamsg.assignments.domain.entity.Department;
import com.sohamsg.assignments.domain.entity.Employee;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest

@Slf4j
class CrudServiceImplTest {

	@Mock
	EmployeeRepository employeeRepository;
	@Mock
	DepartmentRepository departmentRepository;

	@InjectMocks
	CrudServiceImpl crudServiceImpl;

	List<Employee> employees = new ArrayList<>();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		
		employees = new ArrayList<>();

		employees
				.add(Employee.builder().name("Soham").department(Department.builder().name("Tech & Engg").id(1).build())
						.email("soham@comany.com").password("123").role("Tech Arch").build());

		employees.add(
				Employee.builder().name("Soham-Another").department(Department.builder().name("Engg R&D").id(2).build())
						.email("soham2@comany.com").password("123").role("VP Engg").build());
	}

	@AfterEach
	void tearDown() throws Exception {
		employees.clear();
	}

	@Test
	@DisplayName("Find all employees")
	void testFindAllEmployees() {
		when(crudServiceImpl.findAllEmployees()).thenReturn(employees);

//		 List<Employee> allEmployees = crudServiceImpl.findAllEmployees();
//		 assertIterableEquals(allEmployees, employees, "chking if same");
//		verify(employeeRepository,times(1)).findAll();
	}

	@Test
	@Disabled
	void testFindEmployeeById() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testSaveEmployee() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testUpdateEmployee() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testDeleteEmployeeById() {
		fail("Not yet implemented");
	}

}
