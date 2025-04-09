package com.summitlib.resource;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.summitlib.service.BookService;

@Stateless
@Path("/public/book")
public class BookResource {
	
	@Inject
	private BookService bookService;
	
	@GET
	@Path("/ping")
	public String ping() {
		return "Api is working";
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBooks() {
		return bookService.getBooks();
	}
	
	@GET
	@Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response getBooks(@PathParam("id") @NotNull long id) {
		return bookService.getBook(id);
	}
	
	@GET
	@Path("/categories")
    @Produces(MediaType.APPLICATION_JSON)
	public Response getCategories() {
		return bookService.getCategories();
	}
	
	//find book
	
	
	

	
	
	
	
	
	

}
