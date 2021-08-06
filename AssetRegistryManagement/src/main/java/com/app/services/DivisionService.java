package com.app.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.models.Division;
import com.app.models.Division;
import com.app.models.DepreciationMethod;
import com.app.repositories.DivisionRepository;


@Service
public class DivisionService {

	@Autowired
	private DivisionRepository divisionRepo;

	public List<Division> getAllDivisions() {

		List<Division> divisions = divisionRepo.findAll();
		return divisions;
	}
	
	public void save(Division division) {

		divisionRepo.save(division);
		
	}
	
	public void delete(Long id) {

		divisionRepo.deleteById(id);
		
	}
	
	public Page<Division> findPaginatedAssets(Pageable pageable) {

		List<Division> divisions = divisionRepo.findAll();

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Division> list;

		if (divisions.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, divisions.size());
			list = divisions.subList(startItem, toIndex);
		}

		Page<Division> bookPage = new PageImpl<Division>(list, PageRequest.of(currentPage, pageSize), divisions.size());

		return bookPage;
	}


}
