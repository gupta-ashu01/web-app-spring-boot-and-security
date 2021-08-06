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
import com.app.models.Vendor;
import com.app.models.ListAllAssets;
import com.app.services.AssetService;
import com.app.services.AssetTypeService;
import com.app.services.VendorService;
import com.app.services.DepartmentService;
import com.app.services.DepreciationMethodService;
import com.app.services.VendorService;
import com.app.services.EmployeeService;
import com.app.services.RegionService;
import com.app.services.VendorService;
import com.app.services.VendorService;

@Controller
public class MasterVendorController {

	@Autowired
	VendorService vendorService;

	@GetMapping("/master/vendor")
	public String masterAssetType(Model model, HttpSession session, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(10);

		Page<Vendor> vendorPage = vendorService.findPaginatedAssets(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("vendorPage", vendorPage);

		int totalPages = vendorPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		model.addAttribute("vendor", new Vendor());
		return "vendor_master";
	}

	@GetMapping("/master/create-vendor")
	public String masterCreateVendor(Model model) {

		model.addAttribute("vendor", new Vendor());
		return "master_create_vendor";
	}

	@PostMapping("/master/create-vendor")
	public String masterSaveVendor(@ModelAttribute("vendor") Vendor vendor) {
		vendorService.save(vendor);

		return "redirect:/master/vendor";
	}
	@GetMapping("/master/delete-vendor")
	public String delete(@ModelAttribute("vendorId") Long id) {
		vendorService.delete(id);
		return "redirect:/master/vendor";
	}

}
