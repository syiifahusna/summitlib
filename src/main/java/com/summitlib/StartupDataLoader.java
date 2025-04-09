package com.summitlib;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.summitlib.model.Advertisement;
import com.summitlib.model.Category;

@Singleton
@Startup
public class StartupDataLoader {
	
	@PersistenceContext(unitName = "summitLibPu")
    private EntityManager entityManager;
	
	@PostConstruct
    public void init() {
        Long countAds = (Long) entityManager.createQuery("SELECT COUNT(a) FROM Advertisement a").getSingleResult();
        if (countAds == 0) {
            Advertisement ad = new Advertisement("Advertisement title here", "Any message here");
            entityManager.persist(ad);
        }
        
        Long countCategory = (Long) entityManager.createQuery("SELECT COUNT(c) FROM Category c").getSingleResult();
        if (countCategory == 0) {
        	
        	 List<Category> categories = Arrays.asList(
        	            new Category("Fiction", "Imaginative or made-up stories", "fa-book-open"),
        	            new Category("Non-Fiction", "Factual books based on real events and people", "fa-book"),
        	            new Category("Science Fiction", "Futuristic or science-based speculative stories", "fa-robot"),
        	            new Category("Fantasy", "Magical worlds, mythical creatures, epic quests", "fa-hat-wizard"),
        	            new Category("Mystery", "Crime-solving, detective stories", "fa-user-secret"),
        	            new Category("Romance", "Love stories and romantic relationships", "fa-heart"),
        	            new Category("Biography", "Life stories of real people", "fa-user"),
        	            new Category("Self-Help", "Personal development and motivational books", "fa-seedling"),
        	            new Category("History", "Books about historical events and figures", "fa-landmark"),
        	            new Category("Science", "Books on scientific topics and discoveries", "fa-flask"),
        	            new Category("Technology", "Tech trends, computing, and innovation", "fa-microchip"),
        	            new Category("Business", "Entrepreneurship, management, finance", "fa-briefcase"),
        	            new Category("Travel", "Adventure, guides, and cultural exploration", "fa-plane"),
        	            new Category("Cooking", "Recipes, culinary techniques, and food culture", "fa-utensils"),
        	            new Category("Health & Fitness", "Wellness, exercise, and nutrition", "fa-dumbbell"),
        	            new Category("Religion & Spirituality", "Faith-based and spiritual texts", "fa-praying-hands"),
        	            new Category("Art & Photography", "Visual arts, creativity, and photography books", "fa-palette"),
        	            new Category("Comics & Graphic Novels", "Illustrated storytelling and superheroes", "fa-book-journal-whills"),
        	            new Category("Children’s Books", "Books for young readers, often illustrated", "fa-child"),
        	            new Category("Education", "Academic and instructional materials", "fa-graduation-cap")
        	        );
        	
        	 for (Category category : categories) {
                 entityManager.persist(category);
             }
        }
    }

}
