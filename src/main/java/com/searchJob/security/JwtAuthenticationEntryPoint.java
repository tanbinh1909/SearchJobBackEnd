//package com.searchJob.security;
//
//import java.io.IOException;
//import java.io.Serializable;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//
//import com.searchJob.dto.ApiResponse;
//import com.searchJob.utit.JsonUtil;
//
//
//public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
//
//	private static final long serialVersionUID = -7858869558953243875L;
//
//	@Override
//	public void commence(HttpServletRequest request, HttpServletResponse response,
//			AuthenticationException authException) throws IOException, ServletException {
//		ApiResponse res = new ApiResponse();
//	    res.setSuccess(false);
//	    res.setErrorCode(String.valueOf(HttpServletResponse.SC_UNAUTHORIZED));
//	    res.setMessage("Unauthorized");
//	    response.getWriter().append(JsonUtil.convertToJson(res));
//	}
//
//}
