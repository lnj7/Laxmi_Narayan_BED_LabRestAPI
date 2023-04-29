package com.greatlearning.Student.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greatlearning.Student.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("Select u from User u Where u.username = ?1")
	public User getUserByUsername(String username);
}
