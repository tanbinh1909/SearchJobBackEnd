package com.searchJob.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.searchJob.dto.ApiResponse;
import com.searchJob.entity.Upload;
import com.searchJob.repository.UploadReponsitory;

@Transactional(rollbackFor = { Exception.class })
@Service("UploadService")
public class UploadService {
	
	@Autowired
	UploadReponsitory uploadReponsitory;
	
	public ApiResponse saveUploadCV(MultipartFile file, String filePath) throws Exception {
		ApiResponse response = new ApiResponse();
		try {
			Upload upload = new Upload();
			upload.setFileName(file.getOriginalFilename());
			upload.setFilePath(filePath);
			upload.setContentType(file.getContentType());
			upload.setDataCV(file.getBytes());
			upload = uploadReponsitory.save(upload);
			response.setData(upload);
			response.setSuccess(true);
			response.setMessage("Success");
			return response;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setData(null);
			response.setSuccess(false);
			response.setMessage("Error");
		}
		return response;
	}
	public Upload findByUploadCVId(String uploadCVId) {
		return uploadReponsitory.findByUploadCVId(uploadCVId);
	}
}
