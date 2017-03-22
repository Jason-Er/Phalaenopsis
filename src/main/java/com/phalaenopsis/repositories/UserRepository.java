package com.phalaenopsis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phalaenopsis.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findBySsoId(String sso);

}