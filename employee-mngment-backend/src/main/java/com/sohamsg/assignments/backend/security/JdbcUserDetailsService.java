package com.sohamsg.assignments.backend.security;

import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Component;

import com.sohamsg.assignments.backend.reository.EmployeeRepository;
import com.sohamsg.assignments.domain.entity.Employee;


@Component
public class JdbcUserDetailsService extends JdbcUserDetailsManager{
	@Autowired
	private  DataSource dataSource;
	@Autowired
	private EmployeeRepository employeeRepository; 
	
	
	@Autowired
	public JdbcUserDetailsService(DataSource dataSource) {
		super(dataSource);
		
	}



	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		List<Employee> users = employeeRepository.findByEmail(email);
		if (users.size() > 0) {
			String password = users.get(0).getPassword();
			String role = users.get(0).getRole();

			Set<GrantedAuthority> authorities = Set.of(new SimpleGrantedAuthority(role));

			return new org.springframework.security.core.userdetails.User(email, password, authorities);
		} else {
			throw new UsernameNotFoundException("Does not exist- username: " + email);
		}

	}
}
