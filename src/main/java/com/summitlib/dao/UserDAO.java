package com.summitlib.dao;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.summitlib.model.User;

@Stateless
public class UserDAO {
	
	@PersistenceContext(unitName = "summitLibPu")
    private EntityManager entityManager;
	
	
//	public List<User> getUsers(){
//		TypedQuery<User> query = entityManager.createQuery( 
//	            "SELECT u FROM User u WHERE u.role = 'user'", User.class); 
//	        return query.getResultList();
//	}
	
	public User saveUser(User newUser) {
		entityManager.persist(newUser);
		return newUser;
	}
	
	public Boolean isUserExists(String username) {
		
		try {
            Long count = entityManager.createQuery(
                "SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class)
                .setParameter("username", username)
                .getSingleResult();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
		
	}
	
	public Optional<User> findUserByUsername(String username) {
	    try {
	        User user = entityManager
	            .createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
	            .setParameter("username", username)
	            .getSingleResult();
	        return Optional.of(user);
	    } catch (NoResultException e) {
	        return Optional.empty();
	    } catch (Exception e) {
	        e.printStackTrace(); // or use logging
	        return Optional.empty();
	    }
	}
	
	


}
