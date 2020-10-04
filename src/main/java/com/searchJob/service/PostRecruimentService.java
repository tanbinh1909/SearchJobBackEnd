package com.searchJob.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.searchJob.dto.ApiResponse;
import com.searchJob.dto.PostRecruimentDTO;
import com.searchJob.dto.PostRecruimentSearchDTO;
import com.searchJob.entity.PostRecruiment;
import com.searchJob.entity.TypeJob;
import com.searchJob.repository.PostRecruimentRepository;
import com.searchJob.repository.TypeJobRepository;

@Transactional(rollbackFor = { Exception.class })
@Service("PostRecruimentService")
public class PostRecruimentService {

	@Autowired
	PostRecruimentRepository postRecruimentRepository;
	
	@Autowired
	TypeJobRepository typeJobRepository;

	// lay toan bo danh sach bai dang
	public ApiResponse getListPostRecruiment() {
		ApiResponse response = new ApiResponse();
		try {
			List<PostRecruimentDTO> dtos = new ArrayList<PostRecruimentDTO>();
			List<PostRecruiment> list = postRecruimentRepository.getListAllPostRecruiments();
			for (PostRecruiment postRecruiment : list) {
				dtos.add(convertDTO(postRecruiment));
			}
			response.setData(dtos);
			response.setSuccess(true);
			response.setMessage("Success");
		} catch (Exception e) {
			response.setData(null);
			response.setSuccess(false);
			response.setMessage("Error");
		}
		return response;
	}
	
	// lay danh sach bai da xoa
	public ApiResponse getListHistoryDeletePostRecruiment() {
		ApiResponse response = new ApiResponse();
		try {
			List<PostRecruimentDTO> dtos = new ArrayList<PostRecruimentDTO>();
			List<PostRecruiment> list = postRecruimentRepository.getListHistoryDeletePostRecruiment();
			for (PostRecruiment postRecruiment : list) {
				dtos.add(convertDTO(postRecruiment));
			}
			response.setData(dtos);
			response.setSuccess(true);
			response.setMessage("Success");
		} catch (Exception e) {
			response.setData(null);
			response.setSuccess(false);
			response.setMessage("Error");
		}
		return response;
	}

	// tim id bai dang
	public ApiResponse findByIdPost(String id) {
		ApiResponse response = new ApiResponse();
		try {
			PostRecruiment postRecruiment = postRecruimentRepository.findByIdContaining(id);
			if (Objects.nonNull(postRecruiment)) {
				response.setData(postRecruiment);
				response.setSuccess(true);
				response.setMessage("Success");
			}
			response.setData(null);
			response.setSuccess(true);
			response.setMessage("Exit_by_not_id");
		} catch (Exception e) {
			response.setData(null);
			response.setSuccess(false);
			response.setMessage("Error");
		}
		return response;
	}

	// lay danh sach cho slide
	public ApiResponse getListSlidePostRecruiment() {
		ApiResponse response = new ApiResponse();
		try {
			List<Map<String, Object>> listMap = postRecruimentRepository.getListSlidePostRecruiment();
			response.setData(listMap);
			response.setSuccess(true);
			response.setMessage("Success");
		} catch (Exception e) {
			response.setData(null);
			response.setSuccess(false);
			response.setMessage("Error");
		}
		return response;
	}

	// lay danh sach cho mang hinh home goi hang 20 ket qua
	public ApiResponse getListPostRecruimentLimit( int pageCurrent) {
		ApiResponse response = new ApiResponse();
		try {
			List<PostRecruiment> list = postRecruimentRepository.getListPostRecruimentLimit(pageCurrent);
			response.setData(list);
			response.setSuccess(true);
			response.setMessage("Success");
		} catch (Exception e) {
			response.setData(null);
			response.setSuccess(false);
			response.setMessage("Error");
		}
		return response;
	}

	// lay danh sach tim kiem
	public ApiResponse getListSeachPostRecruiment(PostRecruimentSearchDTO postRecruimentSearchDTO) throws Exception {
		ApiResponse response = new ApiResponse();
			String nameTypeJob = !StringUtils.isEmpty(postRecruimentSearchDTO.getNameTypeJob())
					? "%" + postRecruimentSearchDTO.getNameTypeJob() + "%"
					: null;
			String address = !StringUtils.isEmpty(postRecruimentSearchDTO.getAddress())
					? "%" + postRecruimentSearchDTO.getAddress() + "%"
					: null;
			List<PostRecruiment> list = postRecruimentRepository.getListSeachPostRecruiment(nameTypeJob,
					address, postRecruimentSearchDTO.getStartSalary(),
					postRecruimentSearchDTO.getEndSalary());
			response.setData(list);
			response.setSuccess(true);
			response.setMessage("Success");
		return response;

	}

	public ApiResponse createPostRecruiment(PostRecruimentDTO postRecruimentDTO) throws Exception {
		ApiResponse response = new ApiResponse();
		PostRecruiment postRecruiment = convertEntity(postRecruimentDTO);
		postRecruiment.setCreateDate(new Date());
		postRecruiment = postRecruimentRepository.save(postRecruiment);
		PostRecruimentDTO recruimentDTO = convertDTO(postRecruiment);
		response.setData(recruimentDTO);
		response.setSuccess(true);
		response.setMessage("Success");
		return response;
	}

	public ApiResponse updatePostRecruiment(PostRecruimentDTO postRecruimentDTO) throws Exception {
		ApiResponse response = new ApiResponse();
		PostRecruiment postRecruiment = postRecruimentRepository.findByIdContaining(postRecruimentDTO.getId());
		if (Objects.nonNull(postRecruiment)) {
			postRecruiment = convertEntity(postRecruimentDTO);
			postRecruiment = postRecruimentRepository.save(postRecruiment);
			response.setData(postRecruiment);
			response.setSuccess(true);
			response.setMessage("Success");
			return response;
		}
		response.setData(null);
		response.setSuccess(true);
		response.setMessage("Exit_not_postRecruiment");
		return response;
	}
	// xoa trong database
	public ApiResponse deleteHistoryPostRecruiment(String id) {
		ApiResponse response = new ApiResponse();
		try {
			PostRecruiment recruiment = postRecruimentRepository.findByIdContaining(id);
			if (Objects.nonNull(recruiment)) {
				postRecruimentRepository.delete(recruiment);
				response.setSuccess(true);
				response.setMessage("Success");
			}
		} catch (Exception e) {
			response.setData(null);
			response.setSuccess(false);
			response.setMessage("Error");
		}
		return response;
	}
	// thay doi thong tin bai dang
	public ApiResponse deletePostRecruiment(String id) {
		ApiResponse response = new ApiResponse();
		try {
			PostRecruiment recruiment = postRecruimentRepository.findByIdContaining(id);
			if (Objects.nonNull(recruiment)) {
				recruiment.setIdDeleted(new Byte("1"));
				postRecruimentRepository.save(recruiment);
				response.setSuccess(true);
				response.setMessage("Success");
				return response;
			}
			response.setSuccess(true);
			response.setData(null);
			response.setMessage("Exit_not_Recruiment");
		} catch (Exception e) {
			response.setData(null);
			response.setSuccess(false);
			response.setMessage("Error");
		}
		return response;
	}
	
	public ApiResponse getTypeJobs() {
		ApiResponse response = new ApiResponse();
		try {
			List<TypeJob> list = typeJobRepository.findAll();
			response.setData(list);
			response.setSuccess(true);
			response.setMessage("Success");
		} catch (Exception e) {
			// TODO: handle exception
			response.setErrorCode(e.getMessage());
			response.setMessage("Error");
			response.setSuccess(false);
		}
		return response;
	}

	protected PostRecruiment convertEntity(PostRecruimentDTO postRecruimentDTO) {
		PostRecruiment recruiment = new PostRecruiment();
		recruiment.setTitle(postRecruimentDTO.getTitle());
		recruiment.setSalary(postRecruimentDTO.getSalary());
		recruiment.setDateWord(postRecruimentDTO.getDateWord());
		recruiment.setAddress(postRecruimentDTO.getAddress());
		recruiment.setDeadline(postRecruimentDTO.getDeadline());
		recruiment.setDegree(postRecruimentDTO.getDegree());
		recruiment.setDescription(postRecruimentDTO.getDescription());
		recruiment.setSpecialize(postRecruimentDTO.getSpecialize());
		recruiment.setIdCustomer(postRecruimentDTO.getIdCustomer());
		recruiment.setIdTypeJob(postRecruimentDTO.getIdTypeJob());
		recruiment.setNameTypeJob(postRecruimentDTO.getNameTypeJob());
		recruiment.setNameCompony(postRecruimentDTO.getNameCompony());
		recruiment.setStatus(postRecruimentDTO.getStatus());
		return recruiment;
	}

	protected PostRecruimentDTO convertDTO(PostRecruiment postRecruiment) {
		PostRecruimentDTO recruimentDTO = new PostRecruimentDTO();
		recruimentDTO.setId(postRecruiment.getId());
		recruimentDTO.setTitle(postRecruiment.getTitle());
		recruimentDTO.setSalary(postRecruiment.getSalary());
		recruimentDTO.setDateWord(postRecruiment.getDateWord());
		recruimentDTO.setAddress(postRecruiment.getAddress());
		recruimentDTO.setDeadline(postRecruiment.getDeadline());
		recruimentDTO.setDegree(postRecruiment.getDegree());
		recruimentDTO.setDescription(postRecruiment.getDescription());
		recruimentDTO.setSpecialize(postRecruiment.getSpecialize());
		recruimentDTO.setIdCustomer(postRecruiment.getIdCustomer());
		recruimentDTO.setIdTypeJob(postRecruiment.getIdTypeJob());
		recruimentDTO.setNameTypeJob(postRecruiment.getNameTypeJob());
		recruimentDTO.setNameCompony(postRecruiment.getNameCompony());
		recruimentDTO.setStatus(postRecruiment.getStatus());
		recruimentDTO.setCreateDate(postRecruiment.getCreateDate());
		recruimentDTO.setUpdateDate(postRecruiment.getUpdateDate());
		return recruimentDTO;
	}
}
