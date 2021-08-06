package com.app.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.models.Category;
import com.app.models.Department;
import com.app.models.User;
import com.app.models.Vendor;
import com.app.repositories.CategoryRepository;
import com.app.repositories.DepartmentRepository;

import com.app.repositories.VendorRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepo;

	public List<Department> getAllDepartments() {

		List<Department> departments = departmentRepo.findAll();
		return departments;
	}

	public void save(Department department) {

		departmentRepo.save(department);
		
	}
	
	public void delete(Long id) {

		departmentRepo.deleteById(id);
		
	}
	
	public Page<Department> findPaginatedAssets(Pageable pageable) {

		List<Department> departments = departmentRepo.findAll();

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Department> list;

		if (departments.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, departments.size());
			list = departments.subList(startItem, toIndex);
		}

		Page<Department> bookPage = new PageImpl<Department>(list, PageRequest.of(currentPage, pageSize), departments.size());

		return bookPage;
	}
}
