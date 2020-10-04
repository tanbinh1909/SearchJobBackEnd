package com.searchJob.dto;

import java.util.Date;

public class PostRecruimentSearchDTO {
	
	private String nameTypeJob;
	
	private String address;
	
	private float startSalary;
	
	private float endSalary;

	public String getNameTypeJob() {
		return nameTypeJob;
	}

	public void setNameTypeJob(String nameTypeJob) {
		this.nameTypeJob = nameTypeJob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public float getStartSalary() {
		return startSalary;
	}

	public void setStartSalary(float startSalary) {
		this.startSalary = startSalary;
	}

	public float getEndSalary() {
		return endSalary;
	}

	public void setEndSalary(float endSalary) {
		this.endSalary = endSalary;
	}
	
	
}
