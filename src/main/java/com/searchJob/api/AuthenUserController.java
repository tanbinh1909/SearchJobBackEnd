package com.searchJob.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.searchJob.config.JwtTokenUtil;
import com.searchJob.dto.ApiResponse;
import com.searchJob.dto.LoginDTO;
import com.searchJob.entity.User;
import com.searchJob.service.UserDetailsImpl;
import com.searchJob.service.UserService;
import com.searchJob.utit.StatusCustomer;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthenUserController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenUtil jwtUtils;
	
	
	@Autowired
	private UserService userService;
	
	

	@RequestMapping(value = "/api/customer/checkLogin", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ApiResponse authenticateUser(@RequestBody LoginDTO loginDTO) {
		ApiResponse response = new ApiResponse();
		try {
			String username = loginDTO.getUsername();
			String password = loginDTO.getPassword();
			User user = userService.findByUsername(username);
			if (Objects.isNull(user)) {
				response.setSuccess(true);
				response.setMessage("Exit_Not_Username");
				response.setData(user);
				return response;
			}
			if (user.getStatus().contains(StatusCustomer.AWAIT_APPROVAL.toString())) {
				response.setSuccess(true);
				response.setMessage("Unapproved_Account");
				response.setData(user);
				return response;
			}
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(username, password));
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String token = jwtUtils.generateJwtToken(authentication);
			
			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();	
			
			List<String> roles = userDetails.getAuthorities().stream()
					.map(item -> item.getAuthority())
					.collect(Collectors.toList());
			
			Map<String, String> data = new HashMap<String, String>();
			data.put("customerId", user.getId());
			data.put("username", username);
			data.put("token", token);
			data.put("role", roles.get(0));
			response.setSuccess(true);
			response.setMessage("Success");
			response.setData(data);
		} catch (Exception e) {
			response.setData(null);
			response.setSuccess(false);
			response.setMessage("Error");
		}
		return response;

	}
}
