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
public class MasterAssetTypeController {

	@Autowired
	AssetTypeService assetTypeService;

	@GetMapping("/master/asset-type")
	public String masterAssetType(Model model, HttpSession session, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(10);

		Page<AssetType> assetPage = assetTypeService.findPaginatedAssets(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("assetTypePage", assetPage);

		int totalPages = assetPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		model.addAttribute("asset_type", new AssetType());
		return "asset_type_master";
	}

	@PostMapping("/master/asset-type")
	public String masterAssetTypeSave(@ModelAttribute("asset_type") AssetType assetType) {
		assetTypeService.save(assetType);
		return "redirect:/master/asset-type";
	}

	@GetMapping("/master/delete-asset-type")
	public String delete(@ModelAttribute("assettypeid") Long id) {
		assetTypeService.delete(id);
		return "redirect:/master/asset-type";
	}

}
