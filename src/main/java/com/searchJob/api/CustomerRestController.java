package com.searchJob.api;

import java.util.Objects;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.searchJob.dto.ApiResponse;
import com.searchJob.dto.CustomerDTO;
import com.searchJob.entity.User;
import com.searchJob.repository.UserRepository;
import com.searchJob.service.UserService;
import com.searchJob.utit.StatusCustomer;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JavaMailSender emailSender;

	@RequestMapping(value = "/createCustomer", method = RequestMethod.POST)
	public ApiResponse createCustomer(@RequestBody CustomerDTO customerDTO) {
		ApiResponse response = new ApiResponse();
		try {
			User cu = userRepository.findByUsernames(customerDTO.getUsername());
			if (Objects.nonNull(cu) && cu.getStatus().contains(StatusCustomer.APPROVAL.toString())) {
				response.setSuccess(true);
				response.setData(cu);
				response.setMessage("Exit_Customer");
				return response;
			}
			User user = userService.createCustomer(customerDTO);
			if (Objects.isNull(user)) {
				response.setSuccess(true);
				response.setMessage("Customer_IsNull");
				return response;
			}

			// send mail
			if(Objects.nonNull(user.getUsername())) {
				MimeMessage mimeMessage = emailSender.createMimeMessage();
				boolean multipart = true;
				MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, multipart,"utf-8");
				 String htmlMsg = "<h1>Mã Code xác nhận tài khoản của bạn:</h1>"
			                +"<h2 style=\"color:red;\">"+user.getCode()+"</h2>";
				mimeMessage.setContent(htmlMsg, "text/html");	
				mimeMessageHelper.setTo(user.getUsername());
				mimeMessageHelper.setSubject("Xác nhận tài khoản Email");
				mimeMessageHelper.setText("Hello, Thank you! I love you");
		        emailSender.send(mimeMessage);
			}
			response.setSuccess(true);
			response.setData(user);
			response.setMessage("Success");
			
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value = "/changeStatus", method = RequestMethod.GET )
	public ApiResponse changeStatus(@RequestParam(value = "code", required = false) String code) throws Exception {
		return userService.approvalUser(code);
	}
	
	@RequestMapping(value = "/updateCustomer", method = RequestMethod.POST )
	@PreAuthorize("hasRole('ADMIN') or hasRole('ROLE_RECRUIMENT') or hasRole('ROLE_POSTRRECRUIMENT')")
	public ApiResponse updateCustomer(@RequestBody CustomerDTO customerDTO) throws Exception {
		return userService.updateUser(customerDTO);
	}
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.GET )
	@PreAuthorize("hasRole('ADMIN') or hasRole('ROLE_RECRUIMENT') or hasRole('ROLE_POSTRRECRUIMENT')")
	public ApiResponse changePassword(@RequestParam(value= "passwordOld", required = false) String passwordOld, @RequestParam(value ="passwordNew", required = false) String passwordNew) throws Exception {
		return userService.changePassword(passwordNew, passwordOld);
	}
	
	@RequestMapping(value = "/checkUsername", method = RequestMethod.GET )
	public ApiResponse checkUsername(@RequestParam(value= "username", required = false) String username) throws Exception {
		return userService.checkUsername(username);
	}
	@RequestMapping(value = "/forgetPassword", method = RequestMethod.GET )
	public ApiResponse forgetPassword(@RequestParam(value= "code", required = false) String code, @RequestParam(value ="passwordNew", required = false) String passwordNew) throws Exception {
		return userService.forgetPassword(code, passwordNew);
	}
	
	@RequestMapping(value = "/changeUpload", method = RequestMethod.POST )
	@PreAuthorize("hasRole('ADMIN') or hasRole('ROLE_RECRUIMENT') or hasRole('ROLE_POSTRRECRUIMENT')")
	public ApiResponse changeUpload(@RequestBody CustomerDTO customerDTO) throws Exception {
		return userService.changeUpload(customerDTO);
	}
	
	@RequestMapping(value = "findByIdUser", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('ROLE_RECRUIMENT') or hasRole('ROLE_POSTRRECRUIMENT')")
	public ApiResponse findById(@RequestParam(value = "id", required = false) String id) {
		return userService.findById(id);
	}

}
