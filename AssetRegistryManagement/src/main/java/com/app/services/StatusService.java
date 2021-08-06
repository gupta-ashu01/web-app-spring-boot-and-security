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
import com.app.models.Status;
import com.app.models.Status;
import com.app.models.User;
import com.app.models.Vendor;
import com.app.repositories.CategoryRepository;

import com.app.repositories.StatusRepository;
import com.app.repositories.VendorRepository;

@Service
public class StatusService {

	@Autowired
	private StatusRepository statusRepo;

	public List<Status> getAllStatusList() {

		List<Status> statusList = statusRepo.findAll();
		return statusList;
	}
	public void save(Status status) {

		statusRepo.save(status);
		
	}
	
	public void delete(Long id) {

		statusRepo.deleteById(id);
		
	}
	
	public Page<Status> findPaginatedAssets(Pageable pageable) {

		List<Status> statuss = statusRepo.findAll();

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Status> list;

		if (statuss.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, statuss.size());
			list = statuss.subList(startItem, toIndex);
		}

		Page<Status> bookPage = new PageImpl<Status>(list, PageRequest.of(currentPage, pageSize), statuss.size());

		return bookPage;
	}




}
