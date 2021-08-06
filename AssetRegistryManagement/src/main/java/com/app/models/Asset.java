package com.app.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "assets")
public class Asset {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "asset_no", nullable = false, length=60, unique=true)
	private String assetNo;

	@Column(name = "asset_description", nullable = false, length=60)
	private String assetDescription;

	//
	@Column(name = "asset_type", nullable = false, length=60)
	private String assetType;
	
	@Column(name = "asset_qty", nullable = false)
	private int assetQty;

	@Column(name = "asset_category_id", nullable = false, length=60)
	private int assetCategoryID;

	@Column(name = "model", nullable = false, length=60)
	private String model;

	@Column(name = "serial_number", nullable = false, length=60)
	private String serialNumber;

	//
	@Column(name = "location", nullable = false, length=60)
	private String location;

	@Column(name = "vendor_id", nullable = false)
	private int vendorID;

	@Column(name = "employee_id", nullable = false)
	private int employeeID;

	@Column(name = "date_acquired", nullable = false, length=60)
	private String dateAcquired;

	@Column(name = "date_sold", nullable = false, length=60)
	private String dateSold;

	//
	@Column(name = "region", nullable = false)
	private int region;

	@Column(name = "department_id", nullable = false)
	private int departmentID;

	//
	@Column(name = "division", nullable = false)
	private int division;

	@Column(name = "status_id", nullable = false)
	private int statusID;

	@Column(name = "purchase_price", nullable = false)
	private double purchasePrice;

	@Column(name = "transport_install", nullable = false)
	private double transportInstall;

	@Column(name = "salvage_value", nullable = false)
	private double salvageValue;

	@Column(name = "depreciated_to_date", nullable = false)
	private double depreciatedToDate;
	
	public double getDepreciatedToDate() {
		return depreciatedToDate;
	}

	public void setDepreciatedToDate(double depreciatedToDate) {
		this.depreciatedToDate = depreciatedToDate;
	}

	public double getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(double currentValue) {
		this.currentValue = currentValue;
	}

	public double getDepreciableBase() {
		return depreciableBase;
	}

	public void setDepreciableBase(double depreciableBase) {
		this.depreciableBase = depreciableBase;
	}

	@Column(name = "current_value", nullable = false)
	private double currentValue;
	
	@Column(name = "depreciable_base", nullable = false)
	private double depreciableBase;
	
	
	@Column(name = "next_sched_maint", nullable = false, length=60)
	private String nextSchedMaint;

	@Column(name = "depreciation_method", nullable = false, length=60)
	private String depreciationMethod;

	@Column(name = "depreciable_life", nullable = false)
	private float depreciableLife;

	@Column(name = "comments",length = 16777215)
	private String comments;

	/*
	 * @Column(name = "make", nullable = false) private String make;
	 * 
	 * @Column(name = "model_number", nullable = false) private String modelNumber;
	 */

	/*
	 * @Column(name = "barcode_number", nullable = false) private String
	 * barcodeNumber;
	 */

	public Long getId() {
		return id;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public int getAssetQty() {
		return assetQty;
	}

	public void setAssetQty(int assetQty) {
		this.assetQty = assetQty;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getRegion() {
		return region;
	}

	public void setRegion(int region) {
		this.region = region;
	}

	public int getDivision() {
		return division;
	}

	public void setDivision(int division) {
		this.division = division;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAssetNo() {
		return assetNo;
	}

	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}

	public String getAssetDescription() {
		return assetDescription;
	}

	public void setAssetDescription(String assetDescription) {
		this.assetDescription = assetDescription;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public int getAssetCategoryID() {
		return assetCategoryID;
	}

	public void setAssetCategoryID(int assetCategoryID) {
		this.assetCategoryID = assetCategoryID;
	}

	public int getStatusID() {
		return statusID;
	}

	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}

	public int getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}

	public int getVendorID() {
		return vendorID;
	}

	public void setVendorID(int vendorID) {
		this.vendorID = vendorID;
	}

	/*
	 * public String getMake() { return make; }
	 * 
	 * public void setMake(String make) { this.make = make; }
	 */

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	/*
	 * public String getModelNumber() { return modelNumber; }
	 * 
	 * public void setModelNumber(String modelNumber) { this.modelNumber =
	 * modelNumber; }
	 */

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/*
	 * public String getBarcodeNumber() { return barcodeNumber; }
	 * 
	 * public void setBarcodeNumber(String barcodeNumber) { this.barcodeNumber =
	 * barcodeNumber; }
	 */

	public String getDateAcquired() {
		return dateAcquired;
	}

	public void setDateAcquired(String dateAcquired) {
		this.dateAcquired = dateAcquired;
	}

	public String getDateSold() {
		return dateSold;
	}

	public void setDateSold(String dateSold) {
		this.dateSold = dateSold;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getDepreciationMethod() {
		return depreciationMethod;
	}

	public void setDepreciationMethod(String depreciationMethod) {
		this.depreciationMethod = depreciationMethod;
	}

	public float getDepreciableLife() {
		return depreciableLife;
	}

	public void setDepreciableLife(float depreciableLife) {
		this.depreciableLife = depreciableLife;
	}

	public double getSalvageValue() {
		return salvageValue;
	}

	public void setSalvageValue(double salvageValue) {
		this.salvageValue = salvageValue;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getNextSchedMaint() {
		return nextSchedMaint;
	}

	public void setNextSchedMaint(String nextSchedMaint) {
		this.nextSchedMaint = nextSchedMaint;
	}

	public double getTransportInstall() {
		return transportInstall;
	}

	public void setTransportInstall(double transportInstall) {
		this.transportInstall = transportInstall;
	}

}
