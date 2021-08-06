package com.app.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.models.Category;
import com.app.models.Asset;
import com.app.repositories.AssetRepository;


@Service
public class AssetService {

	@Autowired
	private AssetRepository assetRepo;

	public List<Asset> getAllAssets() {

		List<Asset> assets = assetRepo.findAll();

		return assets;
	}

	public Asset save(Asset asset) {
		Asset savedAsset = assetRepo.save(asset);

		return savedAsset;
	}

	public void delete(Long assetId) {

		assetRepo.deleteById(assetId);

	}

	public Optional<Asset> findAssetById(Long id) {

		Optional<Asset> asset = assetRepo.findById(id);

		if (asset.isPresent()) {
			return asset;
		} else {
			return Optional.of(new Asset());
		}

	}

	public Page<Asset> findAssetByAssetNo(Pageable pageable, String assetNo) {

		Asset asset = assetRepo.findAssetByAssetNo(assetNo);

		List<Asset> assets = new ArrayList<Asset>();

		assets.add(asset);
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Asset> list;

		if (assets.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, assets.size());
			list = assets.subList(startItem, toIndex);
		}

		Page<Asset> bookPage = new PageImpl<Asset>(list, PageRequest.of(currentPage, pageSize), assets.size());

		return bookPage;

	}

	public Page<Asset> findPaginatedAssets(Pageable pageable) {

		List<Asset> assets = assetRepo.findAll();

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Asset> list;

		if (assets.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, assets.size());
			list = assets.subList(startItem, toIndex);
		}

		Page<Asset> bookPage = new PageImpl<Asset>(list, PageRequest.of(currentPage, pageSize), assets.size());

		return bookPage;
	}

}
