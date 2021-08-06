package com.app.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.models.Vendor;

import com.app.repositories.VendorRepository;

@Service
public class VendorService {

	@Autowired
	private VendorRepository vendorRepo;

	public List<Vendor> getAllVendors() {

		List<Vendor> vendors = vendorRepo.findAll();
		return vendors;
	}

	public void save(Vendor vendor) {

		vendorRepo.save(vendor);

	}

	public void delete(Long id) {

		vendorRepo.deleteById(id);

	}

	public Page<Vendor> findPaginatedAssets(Pageable pageable) {

		List<Vendor> vendors = vendorRepo.findAll();

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Vendor> list;

		if (vendors.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, vendors.size());
			list = vendors.subList(startItem, toIndex);
		}

		Page<Vendor> bookPage = new PageImpl<Vendor>(list, PageRequest.of(currentPage, pageSize), vendors.size());

		return bookPage;
	}
}
