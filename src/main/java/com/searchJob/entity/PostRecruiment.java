package com.searchJob.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.searchJob.utit.GenerateIDUtils;

@Entity
@Table(name = "post_recruiment")
@NamedQuery(name = "PostRecruiment.findAll", query = "SELECT p FROM PostRecruiment p")
public class PostRecruiment implements Serializable {
	
	private static long serialVersionUID = 1L;
	
	@Id
	private String id = GenerateIDUtils.getUUID("");
	
	private String title;
	
	private float salary;
	
	private String degree;
	
	private String dateWord;
	
	private String deadline;
	
	private String image;
	
	private String specialize;
	
	private String status;
	
	private String description;
	
	private String idCustomer;
	
	private String address;
	
	private String idTypeJob;
	
	private String nameTypeJob;
	
	private String nameCompony;
	
	private byte idDeleted;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getDateWord() {
		return dateWord;
	}

	public void setDateWord(String dateWord) {
		this.dateWord = dateWord;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSpecialize() {
		return specialize;
	}

	public void setSpecialize(String specialize) {
		this.specialize = specialize;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdTypeJob() {
		return idTypeJob;
	}

	public void setIdTypeJob(String idTypeJob) {
		this.idTypeJob = idTypeJob;
	}

	public String getNameTypeJob() {
		return nameTypeJob;
	}

	public void setNameTypeJob(String nameTypeJob) {
		this.nameTypeJob = nameTypeJob;
	}

	public String getNameCompony() {
		return nameCompony;
	}

	public void setNameCompony(String nameCompony) {
		this.nameCompony = nameCompony;
	}

	public byte getIdDeleted() {
		return idDeleted;
	}

	public void setIdDeleted(byte idDeleted) {
		this.idDeleted = idDeleted;
	}
	
	
}
