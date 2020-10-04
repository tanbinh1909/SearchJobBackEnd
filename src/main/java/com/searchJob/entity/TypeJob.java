package com.searchJob.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.searchJob.utit.GenerateIDUtils;

@Entity
@Table(name = "type_Job")
@NamedQuery(name = "TypeJob.findAll", query = "SELECT t FROM TypeJob t")
public class TypeJob implements Serializable {
	
	private static long serialVersionUID = 1L;
	
	@Id
	private String id = GenerateIDUtils.getUUID("");
	
	private String nameType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNameType() {
		return nameType;
	}

	public void setNameType(String nameType) {
		this.nameType = nameType;
	}
	
	
}
