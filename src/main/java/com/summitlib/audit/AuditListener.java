package com.summitlib.audit;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class AuditListener {
	
	 	@PrePersist
	    public void prePersist(Auditable auditable) {
	        auditable.setCreatedDate(LocalDateTime.now());
	    }
	    
	    @PreUpdate
	    public void preUpdate(Auditable auditable) {
	        auditable.setUpdatedDate(LocalDateTime.now());
	    }

}
