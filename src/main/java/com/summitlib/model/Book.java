package com.summitlib.model;

import java.io.Serializable;
import java.util.List;

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
	private List<Author> authors;
	private int rating;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id",referencedColumnName = "id",foreignKey = @ForeignKey(name="fk_book_comment"))
	private List<Comment> comments;
	private String desc;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="category_id", foreignKey = @ForeignKey(name="fk_category_id"))
	private Category category;
	private int edition;
	private String language;
	private int isbn10;
	private int isbn13;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="publisher_id", foreignKey = @ForeignKey(name="fk_publisher_id"))
	private Publisher publisher;
	private int pages;
	private boolean available;
	
	
	public Book() {}
	
	public Book(Long id, Image img, String title, List<Author> authors, int rating, List<Comment> comments, String desc,
			Category category, int edition, String language, int isbn10, int isbn13, Publisher publisher, int pages,
			boolean available) {
		this.id = id;
		this.img = img;
		this.title = title;
		this.authors = authors;
		this.rating = rating;
		this.comments = comments;
		this.desc = desc;
		this.category = category;
		this.edition = edition;
		this.language = language;
		this.isbn10 = isbn10;
		this.isbn13 = isbn13;
		this.publisher = publisher;
		this.pages = pages;
		this.available = available;
	}

	public Book(Image img, String title, List<Author> authors, int rating, List<Comment> comments, String desc,
			Category category, int edition, String language, int isbn10, int isbn13, Publisher publisher, int pages,
			boolean available) {
		this.img = img;
		this.title = title;
		this.authors = authors;
		this.rating = rating;
		this.comments = comments;
		this.desc = desc;
		this.category = category;
		this.edition = edition;
		this.language = language;
		this.isbn10 = isbn10;
		this.isbn13 = isbn13;
		this.publisher = publisher;
		this.pages = pages;
		this.available = available;
	}



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


	public List<Author> getAuthors() {
		return authors;
	}


	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
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


	public int getIsbn10() {
		return isbn10;
	}


	public void setIsbn10(int isbn10) {
		this.isbn10 = isbn10;
	}


	public int getIsbn13() {
		return isbn13;
	}


	public void setIsbn13(int isbn13) {
		this.isbn13 = isbn13;
	}


	public Publisher getPublisher() {
		return publisher;
	}


	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}


	public int getPages() {
		return pages;
	}


	public void setPages(int pages) {
		this.pages = pages;
	}


	public boolean isAvailable() {
		return available;
	}


	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	
	
	


	

}
