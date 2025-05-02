package com.summitlib;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;

import com.summitlib.dao.BookDAO;
import com.summitlib.dao.CategoryDAO;
import com.summitlib.model.Advertisement;
import com.summitlib.model.Author;
import com.summitlib.model.Book;
import com.summitlib.model.Category;
import com.summitlib.payload.ApiErrorResponse;
import com.summitlib.payload.ApiResponse;
import com.summitlib.payload.BookRequest;

@Singleton
@Startup
public class StartupDataLoader {
	
	@PersistenceContext(unitName = "summitLibPu")
    private EntityManager entityManager;
	
	@Inject
	private BookDAO bookDAO;
	
	@Inject
	private CategoryDAO categoryDAO;
	
	@PostConstruct
    public void init() {
        Long countAds = (Long) entityManager.createQuery("SELECT COUNT(a) FROM Advertisement a").getSingleResult();
        if (countAds == 0) {
            Advertisement ad = new Advertisement("Advertisement title here", "Any message here");
            entityManager.persist(ad);
        }
        
        saveCategory();
        saveBooks(getBooks());
        
    }
	
	private void saveCategory(){
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

	public void saveBooks(List<BookRequest> bookRequests) {		
		
		Long countBook = (Long) entityManager.createQuery("SELECT COUNT(b) FROM Book b").getSingleResult();
        if (countBook == 0) {
	        	for (BookRequest req : bookRequests) {
	    			if(bookDAO.existByISBN10(req.getIsbn10())) {
	    				System.out.println("Skipping book " + req.getTitle());
	    				continue;
	    			}
	    			
	    			if(bookDAO.existByISBN13(req.getIsbn13())) {
	    				System.out.println("Skipping book " + req.getTitle());
	    				continue;
	    			}
	    			
	    			Optional<Category> optionalCategory = categoryDAO.findCategoryById(req.getCategoryId());
	    			Category category = null;
	    			if(optionalCategory.isPresent()) {
	    				category = optionalCategory.get();
	    			}
	    			
	    			Book newBook = new Book(null, 
	    					req.getTitle(), 
	    					req.getAuthors(), 
	    					null, 
	    					req.getDesc(),
	    					null, 
	    					category,
	    					req.getEdition(), 
	    					req.getLanguage(), 
	    					req.getIsbn10(),
	    					req.getIsbn13(), 
	    					req.getPages(), 
	    					true);
	    			
	    			bookDAO.saveBook(newBook);
	    		}
        }
		

	}
	
	private List<BookRequest> getBooks() {
		
        	
            BookRequest book1 = new BookRequest();
            book1.setTitle("The Silent Echo");
            Set<Author> authors1 = new HashSet<>();
            authors1.add(new Author("Elizabeth Morgan"));
            book1.setAuthors(authors1);
            book1.setDesc("A captivating tale of family secrets that unfold when a woman returns to her childhood home after twenty years.");
            book1.setCategoryId(1); // Fiction
            book1.setEdition(1);
            book1.setLanguage("English");
            book1.setIsbn10("1234567890");
            book1.setIsbn13("1234567890123");
            book1.setPublisherId(1001);
            book1.setPages(342);
            
            BookRequest book2 = new BookRequest();
            book2.setTitle("Beyond the Horizon");
            Set<Author> authors2 = new HashSet<>();
            authors2.add(new Author("James Wilson"));
            book2.setAuthors(authors2);
            book2.setDesc("An emotional journey through the lives of interconnected characters in a small coastal town facing dramatic changes.");
            book2.setCategoryId(1); // Fiction
            book2.setEdition(2);
            book2.setLanguage("English");
            book2.setIsbn10("2345678901");
            book2.setIsbn13("2345678901234");
            book2.setPublisherId(1002);
            book2.setPages(418);
            
            // Category 2: Non-Fiction
            BookRequest book3 = new BookRequest();
            book3.setTitle("The Hidden Patterns of Everyday Life");
            Set<Author> authors3 = new HashSet<>();
            authors3.add(new Author("Dr. Sarah Johnson"));
            book3.setAuthors(authors3);
            book3.setDesc("An exploration of the unseen patterns and routines that shape our daily existence and how understanding them can lead to profound personal growth.");
            book3.setCategoryId(2); // Non-Fiction
            book3.setEdition(1);
            book3.setLanguage("English");
            book3.setIsbn10("3456789012");
            book3.setIsbn13("3456789012345");
            book3.setPublisherId(1003);
            book3.setPages(289);
            
            BookRequest book4 = new BookRequest();
            book4.setTitle("Thinking in Systems: A Primer");
            Set<Author> authors4 = new HashSet<>();
            authors4.add(new Author("Dr. Mark Reynolds"));
            authors4.add(new Author("Dr. Emily Chen"));
            book4.setAuthors(authors4);
            book4.setDesc("An accessible introduction to systems thinking and how it can be applied to understand complex problems in business, society, and life.");
            book4.setCategoryId(2); // Non-Fiction
            book4.setEdition(3);
            book4.setLanguage("English");
            book4.setIsbn10("4567890123");
            book4.setIsbn13("4567890123456");
            book4.setPublisherId(1004);
            book4.setPages(256);
            
            // Category 3: Science Fiction
            BookRequest book5 = new BookRequest();
            book5.setTitle("Quantum Echoes");
            Set<Author> authors5 = new HashSet<>();
            authors5.add(new Author("Alexander Kim"));
            book5.setAuthors(authors5);
            book5.setDesc("In a future where consciousness can be transferred between dimensions, a scientist discovers a disturbing pattern in the quantum fabric that threatens all realities.");
            book5.setCategoryId(3); // Science Fiction
            book5.setEdition(1);
            book5.setLanguage("English");
            book5.setIsbn10("5678901234");
            book5.setIsbn13("5678901234567");
            book5.setPublisherId(1005);
            book5.setPages(412);
            
            BookRequest book6 = new BookRequest();
            book6.setTitle("The Last Colony");
            Set<Author> authors6 = new HashSet<>();
            authors6.add(new Author("Sophia Rodriguez"));
            book6.setAuthors(authors6);
            book6.setDesc("Following humanity's last outpost on a distant planet as they struggle with dwindling resources and the discovery of an ancient alien civilization beneath the surface.");
            book6.setCategoryId(3); // Science Fiction
            book6.setEdition(2);
            book6.setLanguage("English");
            book6.setIsbn10("6789012345");
            book6.setIsbn13("6789012345678");
            book6.setPublisherId(1006);
            book6.setPages(378);
            
            // Category 4: Fantasy
            BookRequest book7 = new BookRequest();
            book7.setTitle("The Crimson Crown");
            Set<Author> authors7 = new HashSet<>();
            authors7.add(new Author("Thomas Wright"));
            book7.setAuthors(authors7);
            book7.setDesc("In a world where magic is fading, a young apprentice must recover the legendary Crimson Crown to restore balance before ancient forces awaken.");
            book7.setCategoryId(4); // Fantasy
            book7.setEdition(1);
            book7.setLanguage("English");
            book7.setIsbn10("7890123456");
            book7.setIsbn13("7890123456789");
            book7.setPublisherId(1007);
            book7.setPages(496);
            
            BookRequest book8 = new BookRequest();
            book8.setTitle("Whispers of the Elder Woods");
            Set<Author> authors8 = new HashSet<>();
            authors8.add(new Author("Eleanor Pierce"));
            authors8.add(new Author("William Stone"));
            book8.setAuthors(authors8);
            book8.setDesc("When an ancient forest begins to encroach on neighboring kingdoms, a scholar and a warrior must uncover the truth behind the sentient woods and the forgotten pact that bound them.");
            book8.setCategoryId(4); // Fantasy
            book8.setEdition(2);
            book8.setLanguage("English");
            book8.setIsbn10("8901234567");
            book8.setIsbn13("8901234567890");
            book8.setPublisherId(1008);
            book8.setPages(524);
            
            // Category 5: Mystery
            BookRequest book9 = new BookRequest();
            book9.setTitle("The Vanishing Hour");
            Set<Author> authors9 = new HashSet<>();
            authors9.add(new Author("Richard Blake"));
            book9.setAuthors(authors9);
            book9.setDesc("A detective with a perfect memory can't recall a crucial hour during which a prominent judge was murdered, making himself the prime suspect.");
            book9.setCategoryId(5); // Mystery
            book9.setEdition(1);
            book9.setLanguage("English");
            book9.setIsbn10("9012345678");
            book9.setIsbn13("9012345678901");
            book9.setPublisherId(1009);
            book9.setPages(368);
            
            BookRequest book10 = new BookRequest();
            book10.setTitle("Mirrors of Deception");
            Set<Author> authors10 = new HashSet<>();
            authors10.add(new Author("Victoria Hamilton"));
            book10.setAuthors(authors10);
            book10.setDesc("When identical crimes begin occurring in multiple cities simultaneously, a forensic psychologist must determine if they're dealing with coordinated killers or something more inexplicable.");
            book10.setCategoryId(5); // Mystery
            book10.setEdition(3);
            book10.setLanguage("English");
            book10.setIsbn10("0123456789");
            book10.setIsbn13("0123456789012");
            book10.setPublisherId(1010);
            book10.setPages(402);
            
            // Category 6: Romance
            BookRequest book11 = new BookRequest();
            book11.setTitle("Chance Encounters");
            Set<Author> authors11 = new HashSet<>();
            authors11.add(new Author("Olivia Martinez"));
            book11.setAuthors(authors11);
            book11.setDesc("Two strangers who meet during a delayed flight discover their lives have been intersecting for years without them knowing, leading them to question fate and destiny.");
            book11.setCategoryId(6); // Romance
            book11.setEdition(1);
            book11.setLanguage("English");
            book11.setIsbn10("1122334455");
            book11.setIsbn13("1122334455667");
            book11.setPublisherId(1011);
            book11.setPages(284);
            
            BookRequest book12 = new BookRequest();
            book12.setTitle("Autumn in Paris");
            Set<Author> authors12 = new HashSet<>();
            authors12.add(new Author("Charlotte Davis"));
            book12.setAuthors(authors12);
            book12.setDesc("An American writer finds herself falling for her French publisher while spending a season in Paris, even as her deadline looms and family complications arise.");
            book12.setCategoryId(6); // Romance
            book12.setEdition(2);
            book12.setLanguage("English");
            book12.setIsbn10("2233445566");
            book12.setIsbn13("2233445566778");
            book12.setPublisherId(1012);
            book12.setPages(312);
            
            // Category 7: Biography
            BookRequest book13 = new BookRequest();
            book13.setTitle("Uncharted Waters: The Life of Amelia Earhart");
            Set<Author> authors13 = new HashSet<>();
            authors13.add(new Author("Margaret Wilson"));
            book13.setAuthors(authors13);
            book13.setDesc("A comprehensive biography examining Amelia Earhart's life, achievements, and the enduring mystery of her final flight with newly discovered documents and interviews.");
            book13.setCategoryId(7); // Biography
            book13.setEdition(3);
            book13.setLanguage("English");
            book13.setIsbn10("3344556677");
            book13.setIsbn13("3344556677889");
            book13.setPublisherId(1013);
            book13.setPages(468);
            
            BookRequest book14 = new BookRequest();
            book14.setTitle("The Quantum Visionary: Richard Feynman's Journey");
            Set<Author> authors14 = new HashSet<>();
            authors14.add(new Author( "Dr. Robert Chen"));
            authors14.add(new Author("Dr. Alan Foster"));
            book14.setAuthors(authors14);
            book14.setDesc("An intimate portrait of physicist Richard Feynman's revolutionary ideas, personal struggles, and lasting impact on science and society.");
            book14.setCategoryId(7); // Biography
            book14.setEdition(1);
            book14.setLanguage("English");
            book14.setIsbn10("4455667788");
            book14.setIsbn13("4455667788990");
            book14.setPublisherId(1014);
            book14.setPages(392);
            
            // Category 8: Self-Help
            BookRequest book15 = new BookRequest();
            book15.setTitle("The Clarity Method: Finding Focus in a Distracted World");
            Set<Author> authors15 = new HashSet<>();
            authors15.add(new Author("Dr. Jennifer Hayes"));
            book15.setAuthors(authors15);
            book15.setDesc("A practical guide to developing mental clarity and purpose in an age of digital distraction, with science-backed techniques for improved focus and decision-making.");
            book15.setCategoryId(8); // Self-Help
            book15.setEdition(1);
            book15.setLanguage("English");
            book15.setIsbn10("5566778899");
            book15.setIsbn13("5566778899001");
            book15.setPublisherId(1015);
            book15.setPages(248);
            
            BookRequest book16 = new BookRequest();
            book16.setTitle("Resilient: Building Strength Through Adversity");
            Set<Author> authors16 = new HashSet<>();
            authors16.add(new Author("Michael Robinson"));
            book16.setAuthors(authors16);
            book16.setDesc("Drawing from psychology, neuroscience, and real-life stories, this guide offers strategies to build emotional resilience and transform challenges into opportunities for growth.");
            book16.setCategoryId(8); // Self-Help
            book16.setEdition(2);
            book16.setLanguage("English");
            book16.setIsbn10("6677889900");
            book16.setIsbn13("6677889900112");
            book16.setPublisherId(1016);
            book16.setPages(274);
            
            // Category 9: History
            BookRequest book17 = new BookRequest();
            book17.setTitle("The Forgotten Alliance: The Untold Story of World War II Diplomacy");
            Set<Author> authors17 = new HashSet<>();
            authors17.add(new Author("Professor Daniel Harris"));
            book17.setAuthors(authors17);
            book17.setDesc("A revealing account of lesser-known diplomatic maneuvers that shaped the outcome of World War II, based on recently declassified documents and personal diaries.");
            book17.setCategoryId(9); // History
            book17.setEdition(1);
            book17.setLanguage("English");
            book17.setIsbn10("7788990011");
            book17.setIsbn13("7788990011223");
            book17.setPublisherId(1017);
            book17.setPages(536);
            
            BookRequest book18 = new BookRequest();
            book18.setTitle("Silk Roads and Sea Routes: Trade Networks That Built Civilizations");
            Set<Author> authors18 = new HashSet<>();
            authors18.add(new Author("Dr. Laura Mitchell"));
            authors18.add(new Author("Professor Rajiv Patel"));
            book18.setAuthors(authors18);
            book18.setDesc("An exploration of how ancient trade routes connected distant civilizations and facilitated the exchange of goods, technologies, religions, and ideas across continents.");
            book18.setCategoryId(9); // History
            book18.setEdition(3);
            book18.setLanguage("English");
            book18.setIsbn10("8899001122");
            book18.setIsbn13("8899001122334");
            book18.setPublisherId(1018);
            book18.setPages(492);
            
            // Category 10: Science
            BookRequest book19 = new BookRequest();
            book19.setTitle("The Conscious Universe: The Scientific Truth of Psychic Phenomena");
            Set<Author> authors19 = new HashSet<>();
            authors19.add(new Author("Dr. Benjamin Walker"));
            book19.setAuthors(authors19);
            book19.setDesc("A rigorous examination of experimental evidence for psychic phenomena, analyzing decades of research and proposing new frameworks for understanding consciousness and reality.");
            book19.setCategoryId(10); // Science
            book19.setEdition(2);
            book19.setLanguage("English");
            book19.setIsbn10("9900112233");
            book19.setIsbn13("9900112233445");
            book19.setPublisherId(1019);
            book19.setPages(384);
            
            BookRequest book20 = new BookRequest();
            book20.setTitle("Microcosmos: The World of Soil Ecology");
            Set<Author> authors20 = new HashSet<>();
            authors20.add(new Author("Dr. Sophia Chang"));
            book20.setAuthors(authors20);
            book20.setDesc("A fascinating journey into the complex ecosystem beneath our feet, revealing how soil microorganisms sustain life on Earth and how this knowledge can help address environmental challenges.");
            book20.setCategoryId(10); // Science
            book20.setEdition(1);
            book20.setLanguage("English");
            book20.setIsbn10("0011223344");
            book20.setIsbn13("0011223344556");
            book20.setPublisherId(1020);
            book20.setPages(328);
            
            List<BookRequest> bookList = new ArrayList();
            bookList.add(book1);
            bookList.add(book2);
            bookList.add(book3);
            bookList.add(book4);
            bookList.add(book5);
            bookList.add(book6);
            bookList.add(book7);
            bookList.add(book8);
            bookList.add(book9);
            bookList.add(book10);
            bookList.add(book11);
            bookList.add(book12);
            bookList.add(book13);
            bookList.add(book14);
            bookList.add(book15);
            bookList.add(book16);
            bookList.add(book17);
            bookList.add(book18);
            bookList.add(book19);
            bookList.add(book20);
            
            return bookList;
            
	}
}
