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
	
	//getAll books with status true
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
	
	//get book with status true
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
	
	//save book
	public Book saveBook(Book newBook) {
		  if (newBook.getId() == null) {
		        entityManager.persist(newBook);
		    } else {
		        newBook = entityManager.merge(newBook);
		    }
		    return newBook;
	}
	
	//update Book
	public Book updateBook(Long id,Book updatedBook) {
		 Book existingBook = entityManager.find(Book.class, id);
		    
		    if (existingBook == null) {
		        throw new NoSuchElementException("Book with id " + id + " not found");
		    }
		    
		    updatedBook.setId(id);
		    return entityManager.merge(updatedBook);
	}
	
	//disable book
	public Book disableBook(Long id) {
	    Book book = entityManager.find(Book.class, id);
	    
	    if (book == null) {
	        throw new NoSuchElementException("Book with id " + id + " not found");
	    }
	    
	    book.setStatus(false);
	    entityManager.merge(book);
	    
	    return book;
	}
	
	//get books by categories (pagination)
	public List<Book> findBooksByCategory(Long categoryId, int page, int size) {
	    String jpql = "SELECT b FROM Book b WHERE b.category.id = :categoryId";
	    
	    return entityManager.createQuery(jpql, Book.class)
	                        .setParameter("categoryId", categoryId)
	                        .setFirstResult(page * size) // Offset
	                        .setMaxResults(size)         // Limit
	                        .getResultList();
	}
	
	//get res by categories
	public List<Book> findBooksByRating(int limit, int offset){
		String jpql = "SELECT b FROM Book b " 
				+ "LEFT JOIN FETCH b.authors "
		 		+ "LEFT JOIN FETCH b.publisher "
		 		+ "LEFT JOIN FETCH b.category "
		 		+ "LEFT JOIN FETCH b.comments "
		 		+ "LEFT JOIN FETCH b.img  "
				+ "ORDER BY b.createdDate DESC";

	    return entityManager.createQuery(jpql, Book.class)
		    		 .setFirstResult(offset)
	                 .setMaxResults(limit)
	                 .getResultList();
	}
}
