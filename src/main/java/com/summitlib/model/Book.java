package com.summitlib.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.summitlib.audit.Auditable;

@Entity
@Table(name = "books")
public class Book extends Auditable implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="img_id",referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_img_id"))
	private Image img;
	private String title;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id",referencedColumnName = "id",foreignKey = @ForeignKey(name="fk_book_author"))
	private Set<Author> authors;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="publisher_id", foreignKey = @ForeignKey(name="fk_publisher_id"))
	private Publisher publisher;
	private String description;
	private int rating;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id",referencedColumnName = "id",foreignKey = @ForeignKey(name="fk_book_comment"))
	private Set<Comment> comments;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="category_id", foreignKey = @ForeignKey(name="fk_category_id"))
	private Category category;
	private int edition;
	private String language;
	private BigInteger isbn10;
	private BigInteger isbn13;
	private int pages;
	private boolean status;
	
	
	public Book() {}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Image getImg() {
		return img;
	}


	public void setImg(Image img) {
		this.img = img;
	}


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


	public Publisher getPublisher() {
		return publisher;
	}


	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}


	public Set<Comment> getComments() {
		return comments;
	}


	public void setComments(Set<Comment> comments) {
		this.comments = comments;
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


	public BigInteger getIsbn10() {
		return isbn10;
	}


	public void setIsbn10(BigInteger isbn10) {
		this.isbn10 = isbn10;
	}


	public BigInteger getIsbn13() {
		return isbn13;
	}


	public void setIsbn13(BigInteger isbn13) {
		this.isbn13 = isbn13;
	}


	public int getPages() {
		return pages;
	}


	public void setPages(int pages) {
		this.pages = pages;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}

	


	


	

}
