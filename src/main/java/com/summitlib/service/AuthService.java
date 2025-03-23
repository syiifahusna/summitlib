package com.summitlib.service;

import java.time.LocalDateTime;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.security.enterprise.identitystore.PasswordHash;
import javax.ws.rs.core.Response;

import com.summitlib.dao.UserDAO;
import com.summitlib.enums.Role;
import com.summitlib.model.User;
import com.summitlib.payload.ApiErrorResponse;
import com.summitlib.payload.ApiResponse;
import com.summitlib.payload.LoginRequest;
import com.summitlib.payload.RegisterRequest;

@Stateless
public class AuthService {
	
	@Inject
	private UserDAO userDAO;
	
	public Response saveUser(RegisterRequest registerRequest) {
		
		ApiErrorResponse errorResponse;
		String timeNow = LocalDateTime.now().toString();
		
		if(registerRequest.getUsername().isEmpty() ||  registerRequest.getUsername().length()<5  || registerRequest.getUsername().length() > 30) {
			errorResponse = new ApiErrorResponse(400,"Bad Request","Username is not acceptable",timeNow);
			
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorResponse)
                    .build();
		}
		
		if(registerRequest.getPassword().isEmpty() ||  registerRequest.getPassword().length()<5  || registerRequest.getPassword().length()>30) {
			errorResponse = new ApiErrorResponse(400,"Bad Request","Password is not acceptable",timeNow);
			return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorResponse)
                    .build();
		}
		
		if(userDAO.isUserExists(registerRequest.getUsername())) {
			errorResponse = new ApiErrorResponse(409,"Conflict","Username already existed",timeNow);
			return Response.status(Response.Status.CONFLICT)
            .entity(errorResponse)
            .build();
		}
		
		//todo : hash password
		User newUser = new User(registerRequest.getUsername(),registerRequest.getPassword(),Role.USER.toString());
		userDAO.saveUser(newUser);
		
		ApiResponse<RegisterRequest> apiResponse = new ApiResponse<>(201,"New user registered",null,timeNow);
		
		return Response.status(Response.Status.CREATED)
	            .entity(apiResponse)
	            .build();
	}
	
	public Response authenticateUser(LoginRequest loginRequest) {
		return null;
	}

	

}
