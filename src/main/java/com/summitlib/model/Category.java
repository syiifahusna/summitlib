package com.summitlib.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.summitlib.audit.Auditable;

@Entity
@Table(name = "categories")
public class Category extends Auditable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String category;
	
	private String description;
	
	private String icon;
	
	
	public Category() {}

	public Category(Long id, String category, String description, String icon) {
		this.id = id;
		this.category = category;
		this.description = description;
		this.icon = icon;
	}
	
	public Category(String category, String description, String icon) {
		this.category = category;
		this.description = description;
		this.icon = icon;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
	

}
