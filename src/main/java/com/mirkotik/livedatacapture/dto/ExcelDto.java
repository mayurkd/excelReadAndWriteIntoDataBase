package com.mirkotik.livedatacapture.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ExcelDto {
	
	@Id
	private String name;
	private String age;
	private String dpt;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getDpt() {
		return dpt;
	}
	public void setDpt(String dpt) {
		this.dpt = dpt;
	}
	

}
