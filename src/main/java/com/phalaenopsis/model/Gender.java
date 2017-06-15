package com.phalaenopsis.model;

public enum Gender {
	MALE("MALE"),
	FEMALE("FEMALE");	
	String gender;
	private Gender(String gender){
		this.gender = gender;
	}
}
