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
import com.app.models.Category;
import com.app.models.User;
import com.app.repositories.CategoryRepository;


@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;

	public List<Category> getAllCategories() {

		List<Category> categories = categoryRepo.findAll();
		return categories;
	}
	
	
	public void save(Category category) {

		categoryRepo.save(category);
		
	}
	
	public void delete(Long id) {

		categoryRepo.deleteById(id);
		
	}
	
	public Page<Category> findPaginatedAssets(Pageable pageable) {

		List<Category> categories = categoryRepo.findAll();

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Category> list;

		if (categories.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, categories.size());
			list = categories.subList(startItem, toIndex);
		}

		Page<Category> bookPage = new PageImpl<Category>(list, PageRequest.of(currentPage, pageSize), categories.size());

		return bookPage;
	}


}
