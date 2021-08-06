package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.models.DepreciationMethod;

public interface DepreciationMethodRepository extends JpaRepository<DepreciationMethod, Long>{

}
