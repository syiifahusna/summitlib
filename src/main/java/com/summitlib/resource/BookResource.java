package com.summitlib.resource;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
	
	@GET
	@Path("/categories/{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response getCategory(@PathParam("id") long id) {
		return bookService.getCategory(id);
	}
	
	@GET
	@Path("/category/book/{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response getCategoryBooks(@PathParam("id") long id,
																		@QueryParam("limit") @DefaultValue("10") int limit,
            													       @QueryParam("offset") @DefaultValue("0") int offset) {
		return bookService.getCategoryBooks(id, limit,offset);
	}
	
	@GET
	@Path("/recommended")
    @Produces(MediaType.APPLICATION_JSON)
	public Response getRecommendedBooks(@QueryParam("limit") @DefaultValue("10") int limit,
            																	  @QueryParam("offset") @DefaultValue("0") int offset) {
		return bookService.getRecommendedBooks(limit,offset);
	}
	
	@GET
	@Path("/related/{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response getRelatedBooks(@PathParam("id") long id,
																	@QueryParam("limit") @DefaultValue("10") int limit,
            														@QueryParam("offset") @DefaultValue("0") int offset) {
		return bookService.getRelatedBooks(id,limit,offset);
	}
	
	
	
	

	
	
	
	
	
	

}
