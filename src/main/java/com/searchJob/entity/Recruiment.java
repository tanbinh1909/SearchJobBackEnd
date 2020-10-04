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
@Table(name = "recruiment")
@NamedQuery(name = "Recruiment.findAll", query = "SELECT r FROM Recruiment r")
public class Recruiment implements Serializable {

	private static long serialVersionUID = 1L;

	@Id
	private String id = GenerateIDUtils.getUUID("");

	private String namefileCV;

	private String idPostRecruiment;

	private String idCustomer;

	private String status;
	
	private String uploadCVId;
	
	private byte isDeleted;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static void setSerialVersionUID(long serialVersionUID) {
		Recruiment.serialVersionUID = serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNamefileCV() {
		return namefileCV;
	}

	public void setNamefileCV(String namefileCV) {
		this.namefileCV = namefileCV;
	}

	public String getIdPostRecruiment() {
		return idPostRecruiment;
	}

	public void setIdPostRecruiment(String idPostRecruiment) {
		this.idPostRecruiment = idPostRecruiment;
	}

	public String getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getUploadCVId() {
		return uploadCVId;
	}

	public void setUploadCVId(String uploadCVId) {
		this.uploadCVId = uploadCVId;
	}

	public byte getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(byte isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	

}
