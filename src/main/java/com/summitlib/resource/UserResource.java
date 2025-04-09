package com.summitlib.resource;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/user")
public class UserResource {
	
	@GET
	@Path("/ping")
	public String ping() {
		return "Api is working";
	}
	
	
	@GET
	@Path("/profile")
	@Produces(MediaType.APPLICATION_JSON)
	public Response userProfile() {
		//return user profile info
		return null;
	}
	
	@PUT
	@Path("/profile/{id}/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUserProfile() {
		//update user
		return null;
	}
	
	@DELETE
	@Path("/profile/{id}/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deactiveUserProfile() {
		//deactive user
		return null;
	}
	
	//fav boks
	
	//read list
	
	//comments/rating

}
