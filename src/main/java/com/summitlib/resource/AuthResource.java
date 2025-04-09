package com.summitlib.resource;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.summitlib.model.User;
import com.summitlib.payload.ApiErrorResponse;
import com.summitlib.payload.ApiResponse;
import com.summitlib.payload.LoginRequest;
import com.summitlib.payload.RegisterRequest;
import com.summitlib.service.AuthService;

@Stateless
@Path("/public")
public class AuthResource {
	
	@Inject
	private AuthService authService;
	
	//@Context
	//private UriInfo uriInfo;
	
	@GET
	@Path("/ping")
	public String ping() {
		return "Api is working";
	}
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(@Valid RegisterRequest registerRequest) {
        return authService.saveUser(registerRequest);
		
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticate(@Valid LoginRequest loginRequest) {
		//response with jwt token
		return null;
	}
	

}
