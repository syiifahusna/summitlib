package com.summitlib.payload;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.summitlib.model.Author;
import com.summitlib.model.Category;
import com.summitlib.model.Comment;
import com.summitlib.model.Publisher;

public class BookRequest {
		
	@NotBlank(message = "Title cannot be blank")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;
    
    @NotNull(message = "Authors list cannot be null")
    @Size(min = 1, message = "At least one author is required")
    @Valid
    private List<Author> authors;
    
    @Valid
    private List<Comment> comments;
    
    @NotBlank(message = "Description cannot be blank")
    @Size(min = 10, max = 5000, message = "Description must be between 10 and 5000 characters")
    private String desc;
    
    @NotNull(message = "Category cannot be null")
    private Category category;
    
    private int edition;
    
    @NotBlank(message = "Language cannot be blank")
    @Size(min = 2, max = 50, message = "Language must be between 2 and 50 characters")
    private String language;
    
    @NotNull(message = "ISBN-10 cannot be null")
    @Digits(integer = 10, fraction = 0, message = "ISBN-10 must be a 10-digit number")
    private Integer isbn10;
    
    @NotNull(message = "ISBN-13 cannot be null")
    @Digits(integer = 13, fraction = 0, message = "ISBN-13 must be a 13-digit number")
    private Integer isbn13;
    
    @NotNull(message = "Publisher cannot be null")
    @Valid
    private Publisher publisher;
    
    @NotNull(message = "Number of pages cannot be null")
    @Min(value = 1, message = "Number of pages must be at least 1")
    private Integer pages;
    
    public BookRequest() {}

	public BookRequest(
			@NotBlank(message = "Title cannot be blank") @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters") String title,
			@NotNull(message = "Authors list cannot be null") @Size(min = 1, message = "At least one author is required") @Valid List<Author> authors,
			@Valid List<Comment> comments,
			@NotBlank(message = "Description cannot be blank") @Size(min = 10, max = 5000, message = "Description must be between 10 and 5000 characters") String desc,
			@NotNull(message = "Category cannot be null") Category category, int edition,
			@NotBlank(message = "Language cannot be blank") @Size(min = 2, max = 50, message = "Language must be between 2 and 50 characters") String language,
			@NotNull(message = "ISBN-10 cannot be null") @Digits(integer = 10, fraction = 0, message = "ISBN-10 must be a 10-digit number") Integer isbn10,
			@NotNull(message = "ISBN-13 cannot be null") @Digits(integer = 13, fraction = 0, message = "ISBN-13 must be a 13-digit number") Integer isbn13,
			@NotNull(message = "Publisher cannot be null") @Valid Publisher publisher,
			@NotNull(message = "Number of pages cannot be null") @Min(value = 1, message = "Number of pages must be at least 1") Integer pages) {
		this.title = title;
		this.authors = authors;
		this.comments = comments;
		this.desc = desc;
		this.category = category;
		this.edition = edition;
		this.language = language;
		this.isbn10 = isbn10;
		this.isbn13 = isbn13;
		this.publisher = publisher;
		this.pages = pages;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getEdition() {
		return edition;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Integer getIsbn10() {
		return isbn10;
	}

	public void setIsbn10(Integer isbn10) {
		this.isbn10 = isbn10;
	}

	public Integer getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(Integer isbn13) {
		this.isbn13 = isbn13;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}
    
    
		
		
		
		

}
