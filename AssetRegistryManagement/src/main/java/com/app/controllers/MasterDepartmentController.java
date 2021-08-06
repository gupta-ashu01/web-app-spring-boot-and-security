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
import com.app.models.Department;
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
public class MasterDepartmentController {

	@Autowired
	DepartmentService departmentService;

	@GetMapping("/master/department")
	public String masterAssetType(Model model, HttpSession session, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(10);

		Page<Department> departmentPage = departmentService.findPaginatedAssets(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("departmentPage", departmentPage);

		int totalPages = departmentPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		model.addAttribute("department", new Department());
		return "department_master";
	}

	@PostMapping("/master/department")
	public String masterAssetTypeSave(@ModelAttribute("department") Department department) {
		departmentService.save(department);
		return "redirect:/master/department";
	}

	@GetMapping("/master/delete-department")
	public String delete(@ModelAttribute("departmentId") Long id) {
		departmentService.delete(id);
		return "redirect:/master/department";
	}

}
