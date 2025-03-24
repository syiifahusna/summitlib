package com.summitlib.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import com.summitlib.dao.AdvertisementDAO;
import com.summitlib.model.Advertisement;
import com.summitlib.payload.ApiErrorResponse;
import com.summitlib.payload.ApiResponse;

@Stateless
public class AdvertisementService {
	
	@Inject
	private AdvertisementDAO advertisementDAO;
	
	String timeNow = LocalDateTime.now().toString();
	
	public Response getAvertisement() {
		Optional<Advertisement> optionalAd = advertisementDAO.findAdvertisement();
		if(optionalAd.isEmpty()) {
			ApiErrorResponse<String> errorResponse = new ApiErrorResponse<>(404,"Not Found",null,timeNow);
			return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorResponse)
                    .build();
		}
		
		ApiResponse<Advertisement> apiResponse = new ApiResponse<>(200,"Advertisemen retrieve", optionalAd.get(),timeNow);
		return Response.status(Response.Status.OK)
	            .entity(apiResponse)
	            .build();
	}

}
