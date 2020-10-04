package com.searchJob.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.searchJob.dto.ApiResponse;
import com.searchJob.dto.RecruimentDTO;
import com.searchJob.entity.Recruiment;
import com.searchJob.repository.RecruimentRepository;

@Transactional(rollbackFor = { Exception.class })
@Service("RecruitmentService")
public class RecruitmentService {

	@Autowired
	RecruimentRepository recruimentRepository;

	public ApiResponse createRecruitment(RecruimentDTO recruimentDTO) throws Exception {
		ApiResponse response = new ApiResponse();
		Recruiment recruiment = convertEntity(recruimentDTO);
		recruiment.setCreateDate(new Date());
		recruiment = recruimentRepository.save(recruiment);
		response.setData(recruiment);
		response.setSuccess(true);
		response.setMessage("Success");
		return response;
	}

	public ApiResponse getListRecruitmentByIddPostRecruitmentAndIdCustomer(String idPostRecruiment, String idCustomer) {
		ApiResponse response = new ApiResponse();
		try {
			List<Recruiment> list = recruimentRepository
					.getListRecruitmentByIddPostRecruitmentAndIdCustomer(idPostRecruiment, idCustomer);
			response.setData(list);
			response.setSuccess(true);
			response.setMessage("Success");
			return response;
		} catch (Exception e) {
			// TODO: handle exception
			response.setData(null);
			response.setSuccess(false);
			response.setMessage("Error");
		}
		return response;
	}
	
	public ApiResponse findAllbyIdPostRecruiment(String idPostRecruiment) {
		ApiResponse response = new ApiResponse();
		try {
			List<Recruiment> list = recruimentRepository
					.findByIdPostRecruiment(idPostRecruiment);
			response.setData(list);
			response.setSuccess(true);
			response.setMessage("Success");
			return response;
		} catch (Exception e) {
			// TODO: handle exception
			response.setData(null);
			response.setSuccess(false);
			response.setMessage("Error");
		}
		return response;
	}
	
	public ApiResponse getListRecruitmentByIdCustomer(String idCustomer) {
		ApiResponse response = new ApiResponse();
		try {
			List<Recruiment> list = recruimentRepository.getListRecruitmentByIdCustomer(idCustomer);
			response.setData(list);
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

	protected Recruiment convertEntity(RecruimentDTO recruimentDTO) {
		Recruiment recruiment = new Recruiment();
		recruiment.setNamefileCV(recruimentDTO.getNamefileCV());
		recruiment.setIdPostRecruiment(recruimentDTO.getIdPostRecruiment());
		recruiment.setIdCustomer(recruimentDTO.getIdCustomer());
		recruiment.setUploadCVId(recruimentDTO.getUploadCVId());
		recruiment.setIsDeleted(recruimentDTO.getIsDeleted());
		return recruiment;
	}
}
