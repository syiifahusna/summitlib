package com.summitlib.dao;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.summitlib.model.Category;

@Stateless
public class CategoryDAO {
	
	@PersistenceContext(unitName = "summitLibPu")
    private EntityManager entityManager;
	
	//get  category
	public Optional<Category> findCategoryById(long categoryId){
	    Category category = entityManager.find(Category.class, categoryId);
	    return Optional.ofNullable(category);
	}
	
	//get all categories
	public List<Category> findCategory(){
		 String jpql = "SELECT c FROM Category c ORDER BY c.id ASC";
	     TypedQuery<Category> query = entityManager.createQuery(jpql, Category.class);
	    return query.getResultList();
	}

}
