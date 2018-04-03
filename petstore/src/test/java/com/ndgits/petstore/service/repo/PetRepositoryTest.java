package com.ndgits.petstore.service.repo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.ndgits.petstore.service.BaseTest;
import com.ndgits.petstore.service.model.Category;
import com.ndgits.petstore.service.model.Pet;

public class PetRepositoryTest extends BaseTest {
	@Autowired
	private PetRepository petRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Before
	public void init(){
		assertNotNull(petRepository);
		assertNotNull(categoryRepository);
	}
	@Test
	public void testCreateCategoryAndPet(){
		List<Category> entities = new ArrayList<Category>();
		Category category = new Category();
		category.setName("Cat");
		entities.add(category);
		
		category = new Category();
		category.setName("Dog");
		entities.add(category);
		
		categoryRepository.save(entities);
		
		Iterable<Category> iterable = categoryRepository.findAll();
		
		List<Category> categories = StreamSupport.stream(iterable.spliterator(), false).filter(c->c.getName().equals("Cat")) .collect(Collectors.toList());
		
		assertFalse(CollectionUtils.isEmpty(categories));
		
		category = categories.get(0);
		assertTrue(category.getName().equals("Cat"));
		
		Pet cat = new Pet();
		cat.setCategory(category);
		cat.setCategoryId(category.getId());
		cat.setName("Puspus");
		petRepository.save(cat);
		
		assertNotNull(cat.getId());
		
		assertNotNull(cat.getCategory());
		
		cat = new Pet();
		cat.setCategory(category);
		cat.setCategoryId(category.getId());
		cat.setName("Fluffy");
		petRepository.save(cat);
		
		List<Pet>cats = petRepository.findByCategory(category);
		assertFalse(CollectionUtils.isEmpty(cats));
	}
}
