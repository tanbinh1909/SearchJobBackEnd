package com.searchJob.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.searchJob.dto.ApiResponse;
import com.searchJob.dto.PostRecruimentDTO;
import com.searchJob.dto.PostRecruimentSearchDTO;
import com.searchJob.service.PostRecruimentService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/postRecruiment")
public class PostRecruimentRestController {

	@Autowired
	PostRecruimentService postRecruimentService;
	
	@RequestMapping(value = "/getPostRecruiment", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('ROLE_RECRUIMENT') or hasRole('ROLE_POSTRRECRUIMENT')")
	public ApiResponse getListPostRecruiment() {
		return postRecruimentService.getListPostRecruiment();
	}
	
	@RequestMapping(value = "/createPostRecruiment", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_POSTRRECRUIMENT')")
	public ApiResponse createPostRecruiment(@RequestBody PostRecruimentDTO postRecruimentDTO) throws Exception{
		return postRecruimentService.createPostRecruiment(postRecruimentDTO);
	}
	
	@RequestMapping(value = "/updatePostRecruiment", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_POSTRRECRUIMENT')")
	public ApiResponse updatePostRecruiment(@RequestBody PostRecruimentDTO postRecruimentDTO) throws Exception {
		return postRecruimentService.updatePostRecruiment(postRecruimentDTO);
	}
	
	@RequestMapping(value = "/findByIdPostRecruiment/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_POSTRRECRUIMENT')")
	public ApiResponse findByIdPostRecruiment(@PathVariable("id") String id) {
		return postRecruimentService.findByIdPost(id);
	}
	
	@RequestMapping(value = "/deletePostRecruiment/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_POSTRRECRUIMENT')")
	public ApiResponse deletePostRecruiment(@PathVariable("id") String id) {
		return postRecruimentService.deletePostRecruiment(id);
	}
	
	@RequestMapping(value = "/deleteHistoryPostRecruiment/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_POSTRRECRUIMENT')")
	public ApiResponse deleteHistoryPostRecruiment(@PathVariable("id") String id) {
		return postRecruimentService.deleteHistoryPostRecruiment(id);
	}
	
	@RequestMapping(value = "/getListSlidePostRecruiment", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('ROLE_RECRUIMENT')")
	public ApiResponse getListSlidePostRecruiment() {
		return postRecruimentService.getListSlidePostRecruiment();
	}
	
	@RequestMapping(value = "/getListPostRecruimentLimit", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('ROLE_RECRUIMENT')")
	public ApiResponse getListPostRecruimentLimit(@RequestParam(value = "pageCurrent", required = false) int pageCurrent) {
		return postRecruimentService.getListPostRecruimentLimit(pageCurrent);
	}
	
	@RequestMapping(value = "/getListSeachPostRecruiment", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN') or hasRole('ROLE_RECRUIMENT')")
	public ApiResponse getListSeachPostRecruiment(@RequestBody PostRecruimentSearchDTO postRecruimentSearchDTO) throws Exception{
		return postRecruimentService.getListSeachPostRecruiment(postRecruimentSearchDTO);
	}
	
	@RequestMapping(value = "/getTypeJobs", method = RequestMethod.GET)	
	@PreAuthorize("hasRole('ADMIN') or hasRole('ROLE_RECRUIMENT') or hasRole('ROLE_POSTRRECRUIMENT')")
	public ApiResponse getTypeJobs() {
		return postRecruimentService.getTypeJobs();
	}
	@RequestMapping(value = "/getListHistoryDeletePostRecruiment", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_POSTRRECRUIMENT')")
	public ApiResponse getListHistoryDeletePostRecruiment() {
		return postRecruimentService.getListHistoryDeletePostRecruiment();
	}
}
