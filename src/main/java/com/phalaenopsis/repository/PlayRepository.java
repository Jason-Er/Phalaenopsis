package com.phalaenopsis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phalaenopsis.model.Play;

public interface PlayRepository extends JpaRepository<Play, Long> {

}
