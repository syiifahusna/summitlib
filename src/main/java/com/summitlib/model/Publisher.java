package com.summitlib.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.summitlib.audit.Auditable;

@Entity
@Table(name = "publishers")
public class Publisher extends Auditable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

}
