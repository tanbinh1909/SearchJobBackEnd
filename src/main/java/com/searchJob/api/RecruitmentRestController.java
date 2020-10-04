package com.searchJob.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.searchJob.dto.ApiResponse;
import com.searchJob.dto.RecruimentDTO;
import com.searchJob.service.RecruitmentService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/recruitment")
public class RecruitmentRestController {

	@Autowired
	RecruitmentService recruitmentService;
	
	// 
	@RequestMapping(value = "/getListRecruitmentByIddPostRecruitmentAndIdCustomer/{idPostRecruitment}/{idCustomer}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('ROLE_RECRUIMENT')")
	public ApiResponse getListRecruitmentByIddPostRecruitmentAndIdCustomer(@PathVariable("idPostRecruitment") String idPostRecruitment,@PathVariable("idCustomer") String idCustomer) {
		return recruitmentService.getListRecruitmentByIddPostRecruitmentAndIdCustomer(idPostRecruitment, idCustomer);
	}
	
	@RequestMapping(value = "/getListRecruitmentByIdCustomer/{idCustomer}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('ROLE_RECRUIMENT') or hasRole('ROLE_POSTRRECRUIMENT')")
	public ApiResponse getListRecruitmentByIdCustomer(@PathVariable("idCustomer") String idCustomer) {
		return recruitmentService.getListRecruitmentByIdCustomer(idCustomer);
	}
	
	@RequestMapping(value = "/applyCVRecruitment", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN') or hasRole('ROLE_RECRUIMENT')")
	public ApiResponse applyCVRecruitment(@RequestBody RecruimentDTO recruimentDTO) throws Exception {
		return recruitmentService.createRecruitment(recruimentDTO);
	}
	
	@RequestMapping(value = "/findAllbyIdPostRecruiment/{idPostRecruiment}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_POSTRRECRUIMENT')")
	public ApiResponse findAllbyIdPostRecruiment(@PathVariable("idPostRecruiment") String idPostRecruiment) {
		return recruitmentService.findAllbyIdPostRecruiment(idPostRecruiment);
	}
}
