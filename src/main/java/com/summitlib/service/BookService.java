package com.summitlib.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.core.Response;

import com.summitlib.dao.BookDAO;
import com.summitlib.dao.CategoryDAO;
import com.summitlib.model.Author;
import com.summitlib.model.Book;
import com.summitlib.model.Category;
import com.summitlib.model.Comment;
import com.summitlib.model.Image;
import com.summitlib.model.Publisher;
import com.summitlib.payload.ApiErrorResponse;
import com.summitlib.payload.ApiResponse;
import com.summitlib.payload.BookRequest;

@Stateless
public class BookService {
	
	@Inject
	private BookDAO bookDAO;
	
	@Inject
	private CategoryDAO categoryDAO;
	
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
			ApiErrorResponse<String> errorResponse = new ApiErrorResponse<>(400,"Bad Request","Book with id " + id + " not found",timeNow);
			
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorResponse)
                    .build();
		}
		ApiResponse<Book> apiResponse = new ApiResponse<>(200,"Book retrieve", Optionalbook.get(),timeNow);
		return Response.status(Response.Status.OK)
	            .entity(apiResponse)
	            .build();
	}
	
	public Response saveBook( BookRequest bookRequest) {
		
		Book newBook = new Book(null, 
				bookRequest.getTitle(), 
				bookRequest.getAuthors(), 
				null, 
				bookRequest.getDesc(),
				null, 
				null,
				bookRequest.getEdition(), 
				bookRequest.getLanguage(), 
				bookRequest.getIsbn10(),
				bookRequest.getIsbn13(), 
				bookRequest.getPages(), 
				true);
		
		Book book = bookDAO.saveBook(newBook);
		
		String timeNow = LocalDateTime.now().toString();
		
		ApiResponse<Book> apiResponse = new ApiResponse<>(200,"new book created",book ,timeNow);
		return Response.status(Response.Status.OK)
	            .entity(apiResponse)
	            .build();
	}

	public Response getCategories() {
		List<Category> categories = categoryDAO.findCategory();
		
		String timeNow = LocalDateTime.now().toString();
		ApiResponse<List<Category>> apiResponse = new ApiResponse<>(200,"Categories retrieve", categories,timeNow);
		return Response.status(Response.Status.OK)
	            .entity(apiResponse)
	            .build();
	}
	
	
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
