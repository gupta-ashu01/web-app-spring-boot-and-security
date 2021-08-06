package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.models.Role;
import com.app.models.User;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByRole(String role);
}
