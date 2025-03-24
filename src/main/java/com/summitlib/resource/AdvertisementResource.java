package com.summitlib.resource;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.summitlib.service.AdvertisementService;

@Stateless
@Path("/public")
public class AdvertisementResource {
	
	@Inject
	private AdvertisementService advertisementService;
	
	@GET
	@Path("/advertisement")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAdvertisement() {
		return advertisementService.getAvertisement();
	}

}
