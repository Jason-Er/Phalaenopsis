package com.phalaenopsis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phalaenopsis.model.Scene;

public interface SceneRepository extends JpaRepository<Scene, Long> {

}
