package com.summitlib;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.summitlib.model.Advertisement;

@Singleton
@Startup
public class StartupDataLoader {
	
	@PersistenceContext(unitName = "summitLibPu")
    private EntityManager entityManager;
	
	@PostConstruct
    public void init() {
        // Check if data already exists to avoid duplicate inserts
        Long count = (Long) entityManager.createQuery("SELECT COUNT(a) FROM Advertisement a").getSingleResult();
        if (count == 0) {
            Advertisement ad = new Advertisement("Advertisement title here", "Any message here");
            entityManager.persist(ad);
        }
    }

}
