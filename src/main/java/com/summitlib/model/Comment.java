package com.summitlib.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.summitlib.audit.Auditable;

@Entity
@Table(name = "comments")
public class Comment extends Auditable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String comment;
	

}
