package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.models.Category;
import com.app.models.User;
import com.app.models.Vendor;



public interface VendorRepository extends JpaRepository<Vendor, Long>{

}
