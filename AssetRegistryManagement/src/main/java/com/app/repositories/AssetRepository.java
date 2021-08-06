package com.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.app.models. Asset;

public interface AssetRepository extends JpaRepository< Asset, Long>{
	
	 @Query("SELECT a FROM Asset a WHERE a.assetNo=:no")
	    public Asset findAssetByAssetNo(String no);
	 
	

}
