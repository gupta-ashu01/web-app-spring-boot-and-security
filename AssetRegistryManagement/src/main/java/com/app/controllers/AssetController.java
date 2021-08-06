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
public class AssetController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private VendorService vendorService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private StatusService statusService;

	@Autowired
	private RegionService regionService;

	@Autowired
	private DivisionService divisionService;

	@Autowired
	private AssetTypeService assetTypeService;

	@Autowired
	private AssetService assetService;

	@Autowired
	private DepreciationMethodService depreciationMethodService;

	@GetMapping("/create-asset")
	public String create(Model model, HttpSession session) {

		model.addAttribute("asset", new Asset());
		model.addAttribute("categories", categoryService.getAllCategories());
		model.addAttribute("vendors", vendorService.getAllVendors());
		model.addAttribute("employees", employeeService.getAllEmployees());
		model.addAttribute("departments", departmentService.getAllDepartments());
		model.addAttribute("statusList", statusService.getAllStatusList());
		model.addAttribute("regions", regionService.getAllRegions());
		model.addAttribute("divisions", divisionService.getAllDivisions());
		model.addAttribute("assetTypes", assetTypeService.getAllAssetTypes());
		model.addAttribute("depreciationMethods", depreciationMethodService.getAllDepreMethods());
		model.addAttribute("assetSaved", false);
		model.addAttribute("errorMsg", "");

		return "create_asset";
	}

	@GetMapping("/delete-asset")
	public String delete(Model model, HttpSession session,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size, @RequestParam(value = "assetid", required = true) Long assetId) {

		assetService.delete(assetId);

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(15);

		Page<Asset> assetPage = assetService.findPaginatedAssets(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("assetPage", assetPage);

		int totalPages = assetPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		model.addAttribute("asset", new Asset());
		
		return "all_assets";

	}

	@GetMapping("/update-asset")
	public String update(Model model, @RequestParam(value = "assetid", required = true) Long assetId, HttpSession session) {

		Optional<Asset> asset = assetService.findAssetById(assetId);
		
		asset.ifPresent(o -> model.addAttribute("asset", o));

		
		model.addAttribute("categories", categoryService.getAllCategories());
		model.addAttribute("vendors", vendorService.getAllVendors());
		model.addAttribute("employees", employeeService.getAllEmployees());
		model.addAttribute("departments", departmentService.getAllDepartments());
		model.addAttribute("statusList", statusService.getAllStatusList());
		model.addAttribute("regions", regionService.getAllRegions());
		model.addAttribute("divisions", divisionService.getAllDivisions());
		model.addAttribute("assetTypes", assetTypeService.getAllAssetTypes());
		model.addAttribute("depreciationMethods", depreciationMethodService.getAllDepreMethods());
		model.addAttribute("errorMsg", "");

		return "update_asset";
	}

	@PostMapping("/save-updated-asset")
	public String update(@ModelAttribute("asset") Asset asset, Model model, HttpSession session) {

		asset.setDepreciableBase((asset.getTransportInstall() + asset.getPurchasePrice()) - asset.getSalvageValue());

		asset.setCurrentValue((asset.getTransportInstall() + asset.getPurchasePrice()) - asset.getDepreciatedToDate());
		
		
		
		

		Asset SavedAsset = assetService.save(asset);

		if (SavedAsset != null) {
			model.addAttribute("asset", SavedAsset);

		} else {
			model.addAttribute("asset", new Asset());

		}

		model.addAttribute("categories", categoryService.getAllCategories());
		model.addAttribute("vendors", vendorService.getAllVendors());
		model.addAttribute("employees", employeeService.getAllEmployees());
		model.addAttribute("departments", departmentService.getAllDepartments());
		model.addAttribute("statusList", statusService.getAllStatusList());
		model.addAttribute("regions", regionService.getAllRegions());
		model.addAttribute("divisions", divisionService.getAllDivisions());
		model.addAttribute("assetTypes", assetTypeService.getAllAssetTypes());
		model.addAttribute("depreciationMethods", depreciationMethodService.getAllDepreMethods());

		return "update_asset";
	}

	@GetMapping("/asset-depreciation")
	public String assetDepreciation(Model model, HttpSession session,

			@RequestParam(value = "assetid", required = true) Long assetId) {

		assetService.findAssetById(assetId);

		return "asset_depreciation";
	}

	@GetMapping("/all-assets")
	public String allAssets(Model model, HttpSession session, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(15);

		Page<Asset> assetPage = assetService.findPaginatedAssets(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("assetPage", assetPage);

		int totalPages = assetPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		model.addAttribute("asset", new Asset());
		return "all_assets";
	}

	@PostMapping("/search-asset")
	public String searchAsset(Model model, HttpSession session, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, @ModelAttribute("asset") Asset asset) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(15);

		Page<Asset> assetPage = assetService.findAssetByAssetNo(PageRequest.of(currentPage - 1, pageSize),
				asset.getAssetNo());

		model.addAttribute("assetPage", assetPage);

		int totalPages = assetPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		return "all_assets";
	}

	@PostMapping("/save-asset")
	public String save(Model model, @ModelAttribute("asset") Asset asset, HttpSession session) {

		asset.setDepreciableBase((asset.getTransportInstall() + asset.getPurchasePrice()) - asset.getSalvageValue());

		asset.setCurrentValue((asset.getTransportInstall() + asset.getPurchasePrice()) - asset.getDepreciatedToDate());

		Asset SavedAsset = assetService.save(asset);

		if (SavedAsset != null) {
			model.addAttribute("asset", SavedAsset);
			model.addAttribute("assetSaved", true);
			model.addAttribute("errorMsg", "");
		} else {
			model.addAttribute("asset", new Asset());
			model.addAttribute("assetSaved", false);
			model.addAttribute("errorMsg", "Asset Not Saved! Please check if all fields are filled!");

		}

		model.addAttribute("categories", categoryService.getAllCategories());
		model.addAttribute("vendors", vendorService.getAllVendors());
		model.addAttribute("employees", employeeService.getAllEmployees());
		model.addAttribute("departments", departmentService.getAllDepartments());
		model.addAttribute("statusList", statusService.getAllStatusList());
		model.addAttribute("regions", regionService.getAllRegions());
		model.addAttribute("divisions", divisionService.getAllDivisions());
		model.addAttribute("assetTypes", assetTypeService.getAllAssetTypes());
		model.addAttribute("depreciationMethods", depreciationMethodService.getAllDepreMethods());

		return "create_asset";
	}

}
