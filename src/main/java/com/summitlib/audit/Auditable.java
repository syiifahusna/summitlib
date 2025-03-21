package com.summitlib.audit;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.summitlib.CustomLocalDateTimeSerializer;

@MappedSuperclass
@EntityListeners(AuditListener.class)
public abstract class Auditable implements Serializable{
	
	@Column(name = "created_date", nullable = false, updatable = false)
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime createdDate;
    
    @Column(name = "created_by", updatable = false)
    private String createdBy;
    
    @Column(name = "updated_date")
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime updatedDate;
    
    @Column(name = "updated_by")
    private String updatedBy;
    
    public Auditable() {}

	public Auditable(LocalDateTime createdDate, String createdBy, LocalDateTime updatedDate, String updatedBy) {
		super();
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
    
    
}
