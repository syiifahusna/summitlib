package com.summitlib.resource;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.summitlib.payload.BookRequest;
import com.summitlib.service.BookService;

@Path("/admin/book")
public class ManageBookResource {
	
	@Inject
	BookService bookService;
	
	@GET
	@Path("/ping")
	public String ping() {
		return "Api is working";
	}
	
	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response newBook(@Valid BookRequest bookRequest) {
		return bookService.saveBook(bookRequest);
	}
	
	
}
