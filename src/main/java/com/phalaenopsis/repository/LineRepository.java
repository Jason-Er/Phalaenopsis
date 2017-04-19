package com.phalaenopsis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phalaenopsis.model.Line;

public interface LineRepository extends JpaRepository<Line, Long> {

}
