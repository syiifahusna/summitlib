package com.summitlib.resource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.summitlib.payload.BookRequest;
import com.summitlib.service.BookService;

@Path("/admin/book")
public class ManageBookResource {
	
	@Inject
	BookService bookService;
	
	@POST
	@Path("/")
	public String newBook(BookRequest bookRequest) {
		return "it workded";
	}
}
