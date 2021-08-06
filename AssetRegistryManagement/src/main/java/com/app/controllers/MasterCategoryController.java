package com.app.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.models.Asset;
import com.app.models.AssetType;
import com.app.models.Category;
import com.app.models.ListAllAssets;
import com.app.services.AssetService;
import com.app.services.AssetTypeService;
import com.app.services.CategoryService;
import com.app.services.DepartmentService;
import com.app.services.DepreciationMethodService;
import com.app.services.DivisionService;
import com.app.services.EmployeeService;
import com.app.services.RegionService;
import com.app.services.StatusService;
import com.app.services.VendorService;

@Controller
public class MasterCategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping("/master/category")
	public String masterAssetType(Model model, HttpSession session, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(10);

		Page<Category> categoryPage = categoryService.findPaginatedAssets(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("categoryPage", categoryPage);

		int totalPages = categoryPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		model.addAttribute("category", new Category());
		return "category_master";
	}

	@PostMapping("/master/category")
	public String masterAssetTypeSave(@ModelAttribute("category") Category category) {
		categoryService.save(category);
		return "redirect:/master/category";
	}

	@GetMapping("/master/delete-category")
	public String delete(@ModelAttribute("categoryId") Long id) {
		categoryService.delete(id);
		return "redirect:/master/category";
	}

}
