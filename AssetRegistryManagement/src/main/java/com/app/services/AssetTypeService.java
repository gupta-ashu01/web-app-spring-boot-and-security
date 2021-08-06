package com.app.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.models.AssetType;
import com.app.models.Asset;
import com.app.repositories.AssetTypeRepository;


@Service
public class AssetTypeService {

	@Autowired
	private AssetTypeRepository assetTypeRepo;

	public List<AssetType> getAllAssetTypes() {

		List<AssetType> assetTypes = assetTypeRepo.findAll();
		return assetTypes;
	}
	
	

	public void save(AssetType assetType) {

		assetTypeRepo.save(assetType);
		
	}
	
	public void delete(Long id) {

		assetTypeRepo.deleteById(id);
		
	}
	
	public Page<AssetType> findPaginatedAssets(Pageable pageable) {

		List<AssetType> assetTypes = assetTypeRepo.findAll();

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<AssetType> list;

		if (assetTypes.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, assetTypes.size());
			list = assetTypes.subList(startItem, toIndex);
		}

		Page<AssetType> bookPage = new PageImpl<AssetType>(list, PageRequest.of(currentPage, pageSize), assetTypes.size());

		return bookPage;
	}

}
