package com.app.models;

import java.util.List;

public class ListAllAssets {

	private List<Asset> listOfAllAssets;

	public List<Asset> getListOfAllAssets() {
		return listOfAllAssets;
	}

	public void setListOfAllAssets(List<Asset> listOfAllAssets) {
		this.listOfAllAssets = listOfAllAssets;
	}

	public void addAsset(Asset asset) {
		this.listOfAllAssets.add(asset);
	}

}
