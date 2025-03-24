package com.summitlib.payload;

import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.summitlib.model.Author;

public class BookRequest {
		
	    @NotBlank(message = "Title is required")
	    @Size(max = 255, message = "Title cannot exceed 255 characters")
	    private String title;

	    @NotEmpty(message = "At least one author is required")
	    private Set<Author> authors;

	    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
	    private String desc;

	    @Positive(message = "Category ID must be positive")
	    private long categoryId;

	    @Min(value = 1, message = "Edition must be at least 1")
	    private int edition;

	    @NotBlank(message = "Language is required")
	    private String language;

	    @Pattern(regexp = "\\d{10}", message = "ISBN-10 must be exactly 10 digits")
	    private String isbn10;

	    @Pattern(regexp = "\\d{13}", message = "ISBN-13 must be exactly 13 digits")
	    private String isbn13;

	    @Positive(message = "Publisher ID must be positive")
	    private long publisherId;

	    @Min(value = 1, message = "Pages must be at least 1")
	    private int pages;

	    
    public BookRequest() {}

    
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
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

	public String getIsbn10() {
		return isbn10;
	}

	public void setIsbn10(String isbn10) {
		this.isbn10 = isbn10;
	}

	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	public long getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(long publisherId) {
		this.publisherId = publisherId;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

    
    
    
		
		
		
		

}
