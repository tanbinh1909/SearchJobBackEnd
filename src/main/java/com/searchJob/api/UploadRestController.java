package com.searchJob.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.searchJob.dto.ApiResponse;
import com.searchJob.entity.Upload;
import com.searchJob.service.UploadService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/upload")
public class UploadRestController {

	@Autowired
	UploadService uploadService;
	@Autowired
    Environment evn;

	@RequestMapping(value = "/saveUploadSV", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN') or hasRole('ROLE_RECRUIMENT') or hasRole('ROLE_POSTRRECRUIMENT')")
	public ApiResponse saveUploadSV(@RequestParam(value = "file", required = false) MultipartFile file)
			throws Exception {
		return uploadService.saveUploadCV(file, evn.getProperty("source.image.path").toString());
	}
	
	@RequestMapping(value = "/downloadFileCV/{uploadCVId}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('ROLE_RECRUIMENT') or hasRole('ROLE_POSTRRECRUIMENT')")
	public ResponseEntity<Resource> downloadFileCV(@PathVariable("uploadCVId") String uploadCVId) {
        // Load file from database
        Upload dbFile = uploadService.findByUploadCVId(uploadCVId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getDataCV()));
    }
}
