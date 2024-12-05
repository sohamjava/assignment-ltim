package com.sohamsg.assignments.backend.reository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.StringUtils;

import com.sohamsg.assignments.domain.entity.Department;
import com.sohamsg.assignments.domain.entity.Employee;

@DataJpaTest
class EmployeeRepositoryTest {

	@Autowired
    private EmployeeRepository employeeRepository;
	
	@Autowired
    private DepartmentRepository departmentRepository;


    private  List<Employee> employees=new ArrayList<>(); 
    private  Department dep1=Department.builder().name("D1").build();
    private  Department dep2=Department.builder().name("D2").build();
    
    @BeforeEach
	void beforeEach() {
		dep1=departmentRepository.save(dep1);
	    assert(	departmentRepository.count()==1);
	    dep2=departmentRepository.save(dep2);
	    assert(	departmentRepository.count()==2);
	    var employee=Employee.builder().name("Soham")
    			.email("soham@xyz.com")
    			.role("user")
    			.password("xxxxx")
    			.department(dep1)
    			.build();
    	employees.add(employee);
    	employee=Employee.builder().name("Soham Another")
    			.email("soham.another@xyz.com")
    			.role("admin")
    			.password("xxxxx")
    			.department(dep2)
    			.build();
    	employees.add(employee);
    	employeeRepository.saveAll(employees);
    	assert(employeeRepository.count()==2);
	}
    
    
	@AfterEach
	public void afterEach() {
		
		employeeRepository.deleteAll();
		assert(employeeRepository.count()==0);
		departmentRepository.deleteAll();
		assert(departmentRepository.count()==0);
		
	}
    
    
    
    
    
    
    @Test
    @DisplayName("test :: testFindAllEmployees")
    public void testFindAllEmployees() {
    	assert(employeeRepository.findAll().size()==2);
    }
    
    @Test
    @DisplayName("test :: testUpdateEmployee")
    public void testUpdateEmployee() {
    	Employee employee = employeeRepository.findAll().get(0);
    	employee.setEmail("newEmail@newDomain.com");
    	Optional<Employee> byId = employeeRepository.findById(employee.getId());
    	assert(byId.get().getEmail().equals(employee.getEmail()));
    }
    
    
    @Test
    @DisplayName("test :: testDeleteEmployee")
    public void testDeleteEmployee() {
    	Employee employee = employeeRepository.findAll().get(0);
    	
    	Optional<Employee> byId = employeeRepository.findById(employee.getId());
    	assert(byId.isPresent());
    	employeeRepository.deleteById(employee.getId());
    	assert(employeeRepository.findById(employee.getId()).isEmpty());
    }
    
    
   
}
