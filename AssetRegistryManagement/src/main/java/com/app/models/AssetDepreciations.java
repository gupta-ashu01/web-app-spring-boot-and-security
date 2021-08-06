package com.app.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "asset_depreciations")
public class AssetDepreciations {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "asset_id", nullable = false)
	private Long assetId;

	@Column(name = "depreciation_date", nullable = false, length = 60)
	private String depreciationDate;

	@Column(name = "depreciation_amount", nullable = false, length = 60)
	private String depreciationAmount;

	@Column(name = "book_value", nullable = false)
	private String bookValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}

	public String getDepreciationDate() {
		return depreciationDate;
	}

	public void setDepreciationDate(String depreciationDate) {
		this.depreciationDate = depreciationDate;
	}

	public String getDepreciationAmount() {
		return depreciationAmount;
	}

	public void setDepreciationAmount(String depreciationAmount) {
		this.depreciationAmount = depreciationAmount;
	}

	public String getBookValue() {
		return bookValue;
	}

	public void setBookValue(String bookValue) {
		this.bookValue = bookValue;
	}
	
	

}
