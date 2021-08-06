package com.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.models.Category;
import com.app.models.Employee;
import com.app.models.User;
import com.app.models.Vendor;
import com.app.repositories.CategoryRepository;
import com.app.repositories.EmployeeRepository;

import com.app.repositories.VendorRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;

	public List<Employee> getAllEmployees() {

		List<Employee> employees = employeeRepo.findAll();
		return employees;
	}

}
