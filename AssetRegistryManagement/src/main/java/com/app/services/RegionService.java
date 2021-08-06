package com.app.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.models.Region;
import com.app.models.Region;
import com.app.models.DepreciationMethod;
import com.app.repositories.RegionRepository;

import com.app.repositories.RegionRepository;

@Service
public class RegionService {

	@Autowired
	private RegionRepository regionRepo;

	public List<Region> getAllRegions() {

		List<Region> regions = regionRepo.findAll();
		return regions;
	}
	
	public void save(Region region) {

		regionRepo.save(region);
		
	}
	
	public void delete(Long id) {

		regionRepo.deleteById(id);
		
	}
	
	public Page<Region> findPaginatedAssets(Pageable pageable) {

		List<Region> regions = regionRepo.findAll();

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Region> list;

		if (regions.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, regions.size());
			list = regions.subList(startItem, toIndex);
		}

		Page<Region> bookPage = new PageImpl<Region>(list, PageRequest.of(currentPage, pageSize), regions.size());

		return bookPage;
	}

}
