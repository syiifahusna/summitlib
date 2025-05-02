package com.summitlib.dao;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.summitlib.model.Book;
import com.summitlib.model.Category;

@Stateless
public class BookDAO {
	
	@PersistenceContext(unitName = "summitLibPu")
    private EntityManager entityManager;
	
	public List<Book> findBooksByStatusIsTrue(){
		
		 String jpql = "SELECT b FROM Book b "
		 		+ "LEFT JOIN FETCH b.authors "
		 		+ "LEFT JOIN FETCH b.publisher "
		 		+ "LEFT JOIN FETCH b.category "
		 		+ "LEFT JOIN FETCH b.comments "
		 		+ "LEFT JOIN FETCH b.img  "
		 		+ "WHERE b.status = true";
			    
			    TypedQuery<Book> query = entityManager.createQuery(jpql, Book.class);
	    return query.getResultList();
		
	}
	
	public Optional<Book> findBookByStatusIsTrue(Long id){
		String jpql = "SELECT b FROM Book b "
				+ "LEFT JOIN FETCH b.authors "
		 		+ "LEFT JOIN FETCH b.publisher "
		 		+ "LEFT JOIN FETCH b.category "
		 		+ "LEFT JOIN FETCH b.comments "
		 		+ "LEFT JOIN FETCH b.img  "
		 		+ "WHERE b.status = true "
				+ "AND b.id =" + id;
	    TypedQuery<Book> query = entityManager.createQuery(jpql, Book.class);
	    try {
	        Book book = query.setMaxResults(1).getSingleResult();
	        return Optional.of(book);
	    } catch (NoResultException e) {
	        return Optional.empty();
	    }
	}
	
	public boolean existByISBN10(String isbn10) {
		String jpql = "SELECT COUNT(b) FROM Book b WHERE b.isbn10 = :isbn10";
	    Long count = entityManager.createQuery(jpql, Long.class)
	                              .setParameter("isbn10", isbn10)
	                              .getSingleResult();
	    return count > 0;
	}
	
	public boolean existByISBN13(String isbn13) {
		 String jpql = "SELECT COUNT(b) FROM Book b WHERE b.isbn13 = :isbn13";
		    Long count = entityManager.createQuery(jpql, Long.class)
		                              .setParameter("isbn13", isbn13)
		                              .getSingleResult();
		    return count > 0;
	}
	
	public Book saveBook(Book newBook) {
		  if (newBook.getId() == null) {
		        entityManager.persist(newBook);
		    } else {
		        newBook = entityManager.merge(newBook);
		    }
		    return newBook;
	}
	
	public Book updateBook(Long id,Book updatedBook) {
		 Book existingBook = entityManager.find(Book.class, id);
		    
		    if (existingBook == null) {
		        throw new NoSuchElementException("Book with id " + id + " not found");
		    }
		    
		    updatedBook.setId(id);
		    return entityManager.merge(updatedBook);
	}
	
	public Book disableBook(Long id) {
	    Book book = entityManager.find(Book.class, id);
	    
	    if (book == null) {
	        throw new NoSuchElementException("Book with id " + id + " not found");
	    }
	    
	    book.setStatus(false);
	    entityManager.merge(book);
	    
	    return book;
	}
	
	public List<Book> findBooksByCategory(Long categoryId, int limit, int offset) {
		  String jpql = "SELECT b FROM Book b " 
					+ "LEFT JOIN FETCH b.authors "
			 		+ "LEFT JOIN FETCH b.publisher "
			 		+ "LEFT JOIN FETCH b.category "
			 		+ "LEFT JOIN FETCH b.comments "
			 		+ "LEFT JOIN FETCH b.img  "
			 		+ "WHERE b.category.id = :categoryId "
			 		+ "AND b.status = true";
		    
		    return entityManager.createQuery(jpql, Book.class)
		                        .setParameter("categoryId", categoryId)
		                   	 	.setFirstResult(offset)
		                   	 	.setMaxResults(limit)
		   	                 	.getResultList();
	}
	
	public List<Book> findBooksByCreatedDate(int limit, int offset){
		String jpql = "SELECT b FROM Book b " 
				+ "LEFT JOIN FETCH b.authors "
		 		+ "LEFT JOIN FETCH b.publisher "
		 		+ "LEFT JOIN FETCH b.category "
		 		+ "LEFT JOIN FETCH b.comments "
		 		+ "LEFT JOIN FETCH b.img  "
		 		+ "WHERE b.status = true "
				+ "ORDER BY b.createdDate DESC";

	    return entityManager.createQuery(jpql, Book.class)
		    		 .setFirstResult(offset)
	                 .setMaxResults(limit)
	                 .getResultList();
	}
	
	public List<Book> findBooksByTitle(String searchTerm, int limit, int offset){
				 String jpql = "SELECT b FROM Book b "
					 		+ "LEFT JOIN FETCH b.authors "
					 		+ "LEFT JOIN FETCH b.publisher "
					 		+ "LEFT JOIN FETCH b.category "
					 		+ "LEFT JOIN FETCH b.comments "
					 		+ "LEFT JOIN FETCH b.img  "
					 		+ "WHERE b.status = true "
					 		+ "AND LOWER(b.title) LIKE LOWER(:searchTerm)";
						    
			    return entityManager.createQuery(jpql, Book.class)
			    				.setParameter("searchTerm", "%" + searchTerm + "%")
			    				.setFirstResult(offset)
				                .setMaxResults(limit)
				                .getResultList();
	}
	
	public List<Book> findBooksByAuthor(String searchTerm, int limit, int offset){
				String jpql = "SELECT DISTINCT b FROM Book b "
			                + "JOIN b.authors a "
			                + "LEFT JOIN FETCH b.authors "
			                + "LEFT JOIN FETCH b.publisher "
			                + "LEFT JOIN FETCH b.category "
			                + "LEFT JOIN FETCH b.comments "
			                + "LEFT JOIN FETCH b.img "
			                + "WHERE b.status = true "
			                + "AND LOWER(a.name) LIKE LOWER(:searchTerm)";
					    
				 return entityManager.createQuery(jpql, Book.class)
		 				.setParameter("searchTerm", "%" + searchTerm + "%")
		 				.setFirstResult(offset)
		                .setMaxResults(limit)
		                .getResultList();
	}
	
	public List<Book> findBooksByISBN(String searchTerm, int limit, int offset){	            
	            String jpql = "SELECT b FROM Book b "
	    		 		+ "LEFT JOIN FETCH b.authors "
	    		 		+ "LEFT JOIN FETCH b.publisher "
	    		 		+ "LEFT JOIN FETCH b.category "
	    		 		+ "LEFT JOIN FETCH b.comments "
	    		 		+ "LEFT JOIN FETCH b.img  "
	    		 		+ "WHERE b.status = true "
	    		 		+ "AND b.isbn13 LIKE :searchTerm "
	    		 		+ "OR b.isbn10 LIKE :searchTerm";
	    			    
	            return entityManager.createQuery(jpql, Book.class)
	     				.setParameter("searchTerm", "%" + searchTerm + "%")
	     				.setFirstResult(offset)
	                    .setMaxResults(limit)
	                    .getResultList();
	}


}
