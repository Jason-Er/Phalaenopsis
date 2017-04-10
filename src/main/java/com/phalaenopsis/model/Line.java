package com.phalaenopsis.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.PersistenceConstructor;

@Entity
public class Line {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(optional= false)
	private Scene scene;
	
	private Long ordinal;

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public Long getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(Long ordinal) {
		this.ordinal = ordinal;
	}
	
	
}
