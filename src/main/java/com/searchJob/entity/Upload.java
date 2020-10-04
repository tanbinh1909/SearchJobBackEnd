package com.searchJob.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.searchJob.utit.GenerateIDUtils;

@Entity
@Table(name = "upload")
@NamedQuery(name = "Upload.findAll", query = "SELECT u FROM Upload u")
public class Upload {
	
	@Id
	private String uploadCVId = GenerateIDUtils.getUUID("");

    private String fileName;
    
    private String filePath;
    
    private String contentType;
    
    @Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] dataCV;

	public String getUploadCVId() {
		return uploadCVId;
	}

	public void setUploadCVId(String uploadCVId) {
		this.uploadCVId = uploadCVId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getDataCV() {
		return dataCV;
	}

	public void setDataCV(byte[] dataCV) {
		this.dataCV = dataCV;
	}
    
    
}
