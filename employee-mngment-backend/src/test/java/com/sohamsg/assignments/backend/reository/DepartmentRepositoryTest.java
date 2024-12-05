package com.sohamsg.assignments.backend.reository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.sohamsg.assignments.domain.entity.Department;
@DataJpaTest
class DepartmentRepositoryTest {

	private  Department dep1 = Department.builder().name("D1").build();
	private  Department dep2 = Department.builder().name("D2").build();
	@Autowired
    private DepartmentRepository departmentRepository;

	@Autowired
	private TestEntityManager entityManager;
	
	
	@BeforeEach
	void saveDepartments() {
		dep1=departmentRepository.save(dep1);
	    assert(	departmentRepository.count()==1);
	    dep2=departmentRepository.save(dep2);
	    assert(	departmentRepository.count()==2);
	}
	@AfterEach
	public void afterEach() {
		departmentRepository.deleteAll();
		assert(departmentRepository.count()==0);
	}
	
	@Test
	@DisplayName("deleteDepartments")
	void deleteDepartments() {
		departmentRepository.delete(dep1);
	    assert(	departmentRepository.count()==1);
	    departmentRepository.delete(dep2);
	    assert(	departmentRepository.count()==0);
	}

	@Test
	@DisplayName("updateDepartments")
	void updateDepartments() {
		dep1.setName("D1-new");
		departmentRepository.save(dep1);
		assert(dep1.getName().equals(departmentRepository.findById(dep1.getId()).get().getName()));
		
	}
}
