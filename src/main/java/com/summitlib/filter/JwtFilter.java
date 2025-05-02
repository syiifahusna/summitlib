package com.summitlib.filter;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;

import javax.annotation.Priority;
import javax.inject.Inject;
import com.summitlib.dao.UserDAO;
import com.summitlib.payload.ApiErrorResponse;
import com.summitlib.util.JwtUtil;

import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JwtFilter implements ContainerRequestFilter{
	
	private static final String AUTHENTICATION_SCHEME = "Bearer";
	
	@Inject
	private JwtUtil jwtUtil;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		 String path = requestContext.getUriInfo().getPath();
		    if (!path.contains("public")) {
		        String authHeader = requestContext.getHeaderString("Authorization");
		        String timeNow = LocalDateTime.now().toString();

		        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
		            ApiErrorResponse<String> errorResponse = 
		            		new ApiErrorResponse<>(401, "Unauthorized", "Unauthorized user", timeNow);
		            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
		                .entity(errorResponse)
		                .type(MediaType.APPLICATION_JSON)
		                .build());
		            return;
		        }

		        String token = authHeader.substring("Bearer ".length());
		        if (!jwtUtil.validateJwtToken(token)) {
		            ApiErrorResponse<String> errorResponse = new ApiErrorResponse<>(401, "Unauthorized", "Invalid token", timeNow);
		            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
		                .entity(errorResponse)
		                .type(MediaType.APPLICATION_JSON)
		                .build());
		        }
		    }
		
	}
	
	
	
	
	
}
