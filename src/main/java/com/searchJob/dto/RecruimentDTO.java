package com.searchJob.dto;

import java.util.Date;

public class RecruimentDTO {
	
	private String namefileCV;
	
	private String idPostRecruiment;

	private String idCustomer;

	private String status;

	private Date createDate;

	private Date updateDate;
	
	private String uploadCVId;
	
	private byte isDeleted;

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
