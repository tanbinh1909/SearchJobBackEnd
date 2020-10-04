package com.searchJob.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.searchJob.dto.ApiResponse;
import com.searchJob.dto.CustomerDTO;
import com.searchJob.entity.ERole;
import com.searchJob.entity.Role;
import com.searchJob.entity.User;
import com.searchJob.repository.RoleRepository;
import com.searchJob.repository.UserRepository;
import com.searchJob.utit.GenerateIDUtils;
import com.searchJob.utit.StatusCustomer;

@Transactional(rollbackFor = { Exception.class })
@Service("UserService")
public class UserService implements UserDetailsService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	JavaMailSender emailSender;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}
	public User findByUsername(String username) {
		return userRepository.findByUsernames(username);
	}

	public User createCustomer(CustomerDTO customerDTO) throws Exception {
		User user = convertEntity(customerDTO);
		Set<String> strRoles = customerDTO.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_POSTRRECRUIMENT)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "recruitment":
					Role modRole = roleRepository.findByName(ERole.ROLE_RECRUIMENT)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_POSTRRECRUIMENT)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		user.setStatus(StatusCustomer.AWAIT_APPROVAL.toString());
		user.setCode(GenerateIDUtils.getUUID(""));
		user.setCreateDate(new Date());
		user = userRepository.save(user);
		return user;
	}
	
	public ApiResponse approvalUser(String code) {
		ApiResponse response = new ApiResponse();
		try {
			User user = userRepository.findByCode(code);
			if (Objects.nonNull(user)) {
				user.setStatus(StatusCustomer.APPROVAL.toString());
				user = userRepository.save(user);
				// send mail
				if(Objects.nonNull(user.getUsername())) {
					MimeMessage mimeMessage = emailSender.createMimeMessage();
					boolean multipart = true;
					MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, multipart,"utf-8");
					 String htmlMsg = "<h3>Xác nhận tài khoản của bạn thành công</h3>";
					mimeMessage.setContent(htmlMsg, "text/html");	
					mimeMessageHelper.setTo(user.getUsername());
					mimeMessageHelper.setSubject("Đăng kí tài khoản thành công");
					mimeMessageHelper.setText("Hello, Thank you! I love you");
			        emailSender.send(mimeMessage);
				}
				response.setData(user);
				response.setSuccess(true);
				response.setMessage("Success");
				return response;
			}
			response.setData(null);
			response.setSuccess(true);
			response.setMessage("Exit_not_user");
		} catch (Exception e) {
			response.setData(null);
			response.setSuccess(false);
			response.setMessage("Error");
		}
		return response;
	}
	
	public ApiResponse updateUser(CustomerDTO customerDTO) {
		ApiResponse response = new ApiResponse();
		try {
			User user = userRepository.findByIds(customerDTO.getId());
			if (Objects.nonNull(user)) {
				user.setFullName(customerDTO.getFullName());
				user.setFullName(customerDTO.getFullName());
				user.setGender(customerDTO.getGender());
				user.setPhone(customerDTO.getPhone());
				user.setCompanyName(customerDTO.getCompanyName());
				user.setAddress(customerDTO.getAddress());
				user.setUploadId(customerDTO.getUploadId());
				user.setUpdateDate(new Date());
				user = userRepository.save(user);
				// send mail
				if(Objects.nonNull(user.getUsername())) {
					MimeMessage mimeMessage = emailSender.createMimeMessage();
					boolean multipart = true;
					MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, multipart,"utf-8");
					 String htmlMsg = "<h3>Cập nhật tài khoản của bạn thành công</h3>";
					mimeMessage.setContent(htmlMsg, "text/html");	
					mimeMessageHelper.setTo(user.getUsername());
					mimeMessageHelper.setSubject("Cập nhật tài khoản thành công");
					mimeMessageHelper.setText("Hello, Thank you! I love you");
			        emailSender.send(mimeMessage);
				}
				response.setData(user);
				response.setSuccess(true);
				response.setMessage("Success");
				return response;
			}
			response.setData(null);
			response.setSuccess(true);
			response.setMessage("Exit_not_user");
		} catch (Exception e) {
			response.setData(null);
			response.setSuccess(false);
			response.setMessage("Error");
		}
		return response;
	}
	
	public ApiResponse changePassword(String passwordNew, String passwordOld) {
		ApiResponse response = new ApiResponse();
		try {
			User user = userRepository.findByPassword(passwordOld);
			if (Objects.nonNull(user)) {
				user.setPassword(passwordEncoder.encode(passwordNew));
				user = userRepository.save(user);
				// send mail
				if(Objects.nonNull(user.getUsername())) {
					MimeMessage mimeMessage = emailSender.createMimeMessage();
					boolean multipart = true;
					MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, multipart,"utf-8");
					 String htmlMsg = "<h3>Thay đổi mật khẩu của bạn thành công</h3>";
					mimeMessage.setContent(htmlMsg, "text/html");	
					mimeMessageHelper.setTo(user.getUsername());
					mimeMessageHelper.setSubject("Thay đổi mật khẩu tài khoản thành công");
					mimeMessageHelper.setText("Hello, Thank you! I love you");
			        emailSender.send(mimeMessage);
				}
				response.setData(user);
				response.setSuccess(true);
				response.setMessage("Success");
				return response;
			}
			response.setData(null);
			response.setSuccess(true);
			response.setMessage("Exist_not_password");
		} catch (Exception e) {
			response.setData(null);
			response.setSuccess(false);
			response.setMessage("Error");
		}
		return response;
	}
	
	public ApiResponse checkUsername(String username) {
		ApiResponse response = new ApiResponse();
		try {
			User user = userRepository.findByUsernames(username);
			if (Objects.nonNull(user)) {
				user.setCode(GenerateIDUtils.getUUID(""));
				user = userRepository.save(user);
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
				response.setData(user);
				response.setSuccess(true);
				response.setMessage("Success");
				return response;
			}
			response.setData(null);
			response.setSuccess(true);
			response.setMessage("Exist_not_username");
		} catch (Exception e) {
			response.setData(null);
			response.setSuccess(false);
			response.setMessage("Error");
		}
		return response;
	}
	
	
	public ApiResponse forgetPassword(String code, String passwordNew) {
		ApiResponse response = new ApiResponse();
		try {
			User user = userRepository.findByCode(code);
			if (Objects.nonNull(user)) {
				user.setPassword(passwordEncoder.encode(passwordNew));
				user = userRepository.save(user);
				// send mail
				if(Objects.nonNull(user.getUsername())) {
					MimeMessage mimeMessage = emailSender.createMimeMessage();
					boolean multipart = true;
					MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, multipart,"utf-8");
					 String htmlMsg = "<h3>Cập nhật mật khẩu mới của bạn thành công</h3>";
					mimeMessage.setContent(htmlMsg, "text/html");	
					mimeMessageHelper.setTo(user.getUsername());
					mimeMessageHelper.setSubject("Cập nhật mật khẩu mới");
					mimeMessageHelper.setText("Hello, Thank you! I love you");
			        emailSender.send(mimeMessage);
				}
				response.setData(user);
				response.setSuccess(true);
				response.setMessage("Success");
				return response;
			}
			response.setData(null);
			response.setSuccess(true);
			response.setMessage("Exist_not_Code");
		} catch (Exception e) {
			response.setData(null);
			response.setSuccess(false);
			response.setMessage("Error");
		}
		return response;
	}
	
	
	
	public ApiResponse changeUpload(CustomerDTO customerDTO) {
		ApiResponse response = new ApiResponse();
		try {
			User user = userRepository.findByIds(customerDTO.getId());
			if (Objects.nonNull(user)) {
				user.setUploadId(customerDTO.getUploadId());
				user = userRepository.save(user);
				response.setData(user);
				response.setSuccess(true);
				response.setMessage("Success");
				return response;
			}
			response.setData(null);
			response.setSuccess(true);
			response.setMessage("Exit_not_user");
		} catch (Exception e) {
			response.setData(null);
			response.setSuccess(false);
			response.setMessage("Error");
		}
		return response;
	}
	
	public ApiResponse findById(String id) {
		ApiResponse response = new ApiResponse();
		try {
			User user = userRepository.findByIds(id);
			if (Objects.nonNull(user)) {
				response.setData(user);
				response.setSuccess(true);
				response.setMessage("Success");
				return response;
			}
			response.setData(null);
			response.setSuccess(true);
			response.setMessage("Exit_not_user");
		} catch (Exception e) {
			response.setData(null);
			response.setSuccess(false);
			response.setMessage("Error");
		}
		return response;
	}

	protected User convertEntity(CustomerDTO customerDTO) {
		User customer = new User();
		customer.setFullName(customerDTO.getFullName());
		customer.setUsername(customerDTO.getUsername());
		customer.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
		customer.setGender(customerDTO.getGender());
		customer.setPhone(customerDTO.getPhone());
		customer.setCompanyName(customerDTO.getCompanyName());
		customer.setAddress(customerDTO.getAddress());
		customer.setUploadId(customerDTO.getUploadId());
		return customer;
	}
}
