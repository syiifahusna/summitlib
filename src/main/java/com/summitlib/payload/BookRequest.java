package com.summitlib.payload;

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
import com.summitlib.model.Author;
import com.summitlib.model.Category;
import com.summitlib.model.Comment;
import com.summitlib.model.Image;
import com.summitlib.model.Publisher;

public class BookRequest {
		
		private Image img;
		private String title;
		private List<Author> authors;
		private List<Comment> comments;
		private String desc;
		private Category category;
		private int edition;
		private String language;
		private int isbn10;
		private int isbn13;
		private Publisher publisher;
		private int pages;
		private boolean available;
		
		public BookRequest() {}
		
		public BookRequest(Image img, String title, List<Author> authors, List<Comment> comments, String desc,
				Category category, int edition, String language, int isbn10, int isbn13, Publisher publisher, int pages,
				boolean available) {
			super();
			this.img = img;
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
			this.available = available;
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
