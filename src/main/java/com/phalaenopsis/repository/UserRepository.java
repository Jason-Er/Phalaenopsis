package com.phalaenopsis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phalaenopsis.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByName(String name);
}
