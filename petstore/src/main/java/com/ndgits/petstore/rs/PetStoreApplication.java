package com.ndgits.petstore.rs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ndgits.petstore.service.model.Category;
import com.ndgits.petstore.service.model.Pet;
import com.ndgits.petstore.service.repo.CategoryRepository;
import com.ndgits.petstore.service.repo.PetRepository;

@SpringBootApplication
@ComponentScan({"com.ndgits.petstore.rs"
	,"com.ndgits.petstore.rs.swagger"
	,"com.ndgits.petstore.rs.controller"
	,"com.ndgits.petstore.service"})
@EntityScan("com.ndgits.petstore.service.model")
@EnableJpaRepositories("com.ndgits.petstore.service.repo")
public class PetStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetStoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner loadData(PetRepository petRepository, CategoryRepository categoryRepository) {
	    return (args) -> {
			List<Category> entities = new ArrayList<>();
			Category category = new Category();
			category.setName("Cat");
			entities.add(category);
			
			category = new Category();
			category.setName("Dog");
			entities.add(category);
			
			categoryRepository.saveAll(entities);
			Iterable<Category> iterable = categoryRepository.findAll();
			
			List<Category> categories = StreamSupport.stream(iterable.spliterator(), false).filter(c->c.getName().equals("Cat")) .collect(Collectors.toList());
			category = categories.get(0);
			Pet cat = new Pet();
			cat.setCategory(category);
			cat.setCategoryId(category.getId());
			cat.setName("Puspus");
			petRepository.save(cat);
			
			cat = new Pet();
			cat.setCategory(category);
			cat.setCategoryId(category.getId());
			cat.setName("Fluffy");
			petRepository.save(cat);
	    };
	}
}
