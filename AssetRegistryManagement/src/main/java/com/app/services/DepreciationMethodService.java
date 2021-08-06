package com.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.models.AssetType;
import com.app.models.DepreciationMethod;
import com.app.repositories.DepreciationMethodRepository;


@Service
public class DepreciationMethodService {

	@Autowired
	private DepreciationMethodRepository depreciationMethodRepo;

	public List<DepreciationMethod> getAllDepreMethods() {

		List<DepreciationMethod> depreciationMethods = depreciationMethodRepo.findAll();
		return depreciationMethods;
	}

}
