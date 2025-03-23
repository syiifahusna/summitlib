package com.summitlib.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import com.summitlib.dao.BookDAO;
import com.summitlib.model.Book;
import com.summitlib.payload.ApiErrorResponse;
import com.summitlib.payload.ApiResponse;

@Stateless
public class BookService {
	
	ApiErrorResponse errorResponse;
	
	@Inject
	private BookDAO bookDAO;
	
	public Response getBooks() {		
		List<Book> books = bookDAO.findBooksByStatusIsTrue();
		
		String timeNow = LocalDateTime.now().toString();
		ApiResponse<List<Book>> apiResponse = new ApiResponse<>(200,"Books retrieve", books,timeNow);
		return Response.status(Response.Status.OK)
	            .entity(apiResponse)
	            .build();
	}
	
	public Response getBook(Long id) {
		Optional<Book> Optionalbook = bookDAO.findBookByStatusIsTrue(id);
		String timeNow = LocalDateTime.now().toString();
		
		if(Optionalbook.isEmpty()) {
			ApiErrorResponse errorResponse = new ApiErrorResponse(400,"Bad Request","Book with id " + id + " not found",timeNow);
			
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorResponse)
                    .build();
		}
		ApiResponse<Book> apiResponse = new ApiResponse<>(200,"Book retrieve", Optionalbook.get(),timeNow);
		return Response.status(Response.Status.OK)
	            .entity(apiResponse)
	            .build();
	}
	
//	//save book
//	public Response saveBook() {
//		
//	}
//	
//	//update book
//	public Response updateBook() {
//		
//	}
//	
//	//disable book
//	public Response disableBook() {
//		
//	}
	

}
