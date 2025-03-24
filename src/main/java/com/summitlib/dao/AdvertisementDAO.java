package com.summitlib.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AdvertisementDAO {
	
	@PersistenceContext(unitName = "summitLibPu")
    private EntityManager entityManager;
	

}
