package com.summitlib.dao;

import java.util.Optional;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.summitlib.model.Advertisement;

@Stateless
public class AdvertisementDAO {
	
	@PersistenceContext(unitName = "summitLibPu")
    private EntityManager entityManager;
	
	public Optional<Advertisement> findAdvertisement(){
		TypedQuery<Advertisement> query = entityManager.createQuery(
	            "SELECT a FROM Advertisement a ORDER BY a.id ASC", Advertisement.class);
	        query.setMaxResults(1);
	        return query.getResultStream().findFirst();
	}

}
