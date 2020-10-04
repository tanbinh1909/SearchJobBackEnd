package com.searchJob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.searchJob.entity.Upload;

@Repository
public interface UploadReponsitory extends JpaRepository<Upload, String> {

	Upload findByUploadCVId(String uploadCVId);

}
