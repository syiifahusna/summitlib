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
		
		String timeNow = LocalDateTime.now().toString();
		
		if(bookDAO.existByISBN10(bookRequest.getIsbn10())) {
			ApiErrorResponse<String> errorResponse = 
					new ApiErrorResponse<>(409,"Conflict","Isbn10 already exist",timeNow);
			
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorResponse)
                    .build();
		}
		
		if(bookDAO.existByISBN13(bookRequest.getIsbn13())) {
			ApiErrorResponse<String> errorResponse = 
					new ApiErrorResponse<>(409,"Conflict","Isbn13 already exist",timeNow);
			
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorResponse)
                    .build();
		}
		
		Category category = gatCategory(bookRequest.getCategoryId());
		
		Book newBook = new Book(null, 
				bookRequest.getTitle(), 
				bookRequest.getAuthors(), 
				null, 
				bookRequest.getDesc(),
				null, 
				category,
				bookRequest.getEdition(), 
				bookRequest.getLanguage(), 
				bookRequest.getIsbn10(),
				bookRequest.getIsbn13(), 
				bookRequest.getPages(), 
				true);
		
		Book book = bookDAO.saveBook(newBook);
		
		ApiResponse<Book> apiResponse = new ApiResponse<>(200,"New book created",book ,timeNow);
		return Response.status(Response.Status.OK)
	            .entity(apiResponse)
	            .build();
	}
	
	
	public Response saveBooks(List<BookRequest> bookRequests) {
		String timeNow = LocalDateTime.now().toString();
		
		for (BookRequest req : bookRequests) {
			
			if(bookDAO.existByISBN10(req.getIsbn10())) {
				ApiErrorResponse<String> errorResponse = 
						new ApiErrorResponse<>(409,"Conflict","Isbn10 already exist for book " + req.getTitle(),timeNow);
				
	            return Response.status(Response.Status.BAD_REQUEST)
	                    .entity(errorResponse)
	                    .build();
			}
			
			if(bookDAO.existByISBN13(req.getIsbn13())) {
				ApiErrorResponse<String> errorResponse = 
						new ApiErrorResponse<>(409,"Conflict","Isbn13 already exist for book " + req.getTitle(),timeNow);
				
	            return Response.status(Response.Status.BAD_REQUEST)
	                    .entity(errorResponse)
	                    .build();
			}
			
			Category category = gatCategory(req.getCategoryId());
			
			Book newBook = new Book(null, 
					req.getTitle(), 
					req.getAuthors(), 
					null, 
					req.getDesc(),
					null, 
					category,
					req.getEdition(), 
					req.getLanguage(), 
					req.getIsbn10(),
					req.getIsbn13(), 
					req.getPages(), 
					true);
			
			bookDAO.saveBook(newBook);
		}

		ApiResponse<Book> apiResponse = new ApiResponse<>(200,"New books created", null ,timeNow);
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
	

	public Response getCategory(long id) {
		Optional<Category> Optionalcategory = categoryDAO.findCategoryById(id);
		String timeNow = LocalDateTime.now().toString();
		
		if(Optionalcategory.isEmpty()) {
			ApiErrorResponse<String> errorResponse = new ApiErrorResponse<>(400,"Bad Request","Category with id " + id + " not found",timeNow);
			
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorResponse)
                    .build();
		}
		
		ApiResponse<Category> apiResponse = new ApiResponse<>(200,"Categories retrieve", Optionalcategory.get() ,timeNow);
		return Response.status(Response.Status.OK)
	            .entity(apiResponse)
	            .build();
	}
	
	public Response getCategoryBooks(long id, int limit, int offset) {
		List<Book> books = bookDAO.findBooksByCategory(id,  limit, offset);
		String timeNow = LocalDateTime.now().toString();
		ApiResponse<List<Book>> apiResponse = new ApiResponse<>(200,"Books retrieve", books,timeNow);
		return Response.status(Response.Status.OK)
	            .entity(apiResponse)
	            .build();
	}

	public Response getRecommendedBooks(int limit, int offset) {
		List<Book> books = bookDAO.findBooksByCreatedDate(limit,offset);
		String timeNow = LocalDateTime.now().toString();
		ApiResponse<List<Book>> apiResponse = new ApiResponse<>(200,"Books retrieve", books,timeNow);
		return Response.status(Response.Status.OK)
	            .entity(apiResponse)
	            .build();
	}
	
	public Response getRelatedBooks(long id, int limit, int offset) {
		//create algorithm for suggesting related books
		
		String timeNow = LocalDateTime.now().toString();
		ApiResponse<List<Book>> apiResponse = new ApiResponse<>(200,"Books retrieve", null,timeNow);
		return Response.status(Response.Status.OK)
	            .entity(apiResponse)
	            .build();
	}
	
	public Response getSearchBooks(String searchTerm, String type, int limit, int offset) {
		String timeNow = LocalDateTime.now().toString();
		
		if (type == null || type.isEmpty()) {
            type = "title";
        }
		
		 if (searchTerm == null || searchTerm.trim().isEmpty()) {
				ApiErrorResponse<String> errorResponse = new ApiErrorResponse<>(400,"Bad Request","Search cannot be empty",timeNow);
				return Response.status(Response.Status.BAD_REQUEST)
	                    .entity(errorResponse)
	                    .build();
		 }
        
        List<Book> books;
        switch (type.toLowerCase().trim()) {
            case "author":
                books = bookDAO.findBooksByAuthor(searchTerm,limit,offset);
                break;
            case "isbn":
                books = bookDAO.findBooksByISBN(searchTerm,limit,offset);
                break;
            case "title":
            default:
                books = bookDAO.findBooksByTitle(searchTerm,limit,offset);
                break;
        }	
		
		ApiResponse<List<Book>> apiResponse = new ApiResponse<>(200,"Books retrieve", books,timeNow);
		return Response.status(Response.Status.OK)
	            .entity(apiResponse)
	            .build();
	}

	
	public Category gatCategory(long id) {
		//get category object
		Optional<Category> Optionalcategory = categoryDAO.findCategoryById(id);
		
		if(Optionalcategory.isPresent()) {
			return Optionalcategory.get();
		}
		
		return null;
	}
}
