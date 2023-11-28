package com.ndgits.petstore.rs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ndgits.petstore.service.model.Pet;
import com.ndgits.petstore.service.model.PetNotFoundException;
import com.ndgits.petstore.service.repo.PetRepository;

@RestController
@RequestMapping("/pet")
@ConfigurationProperties(prefix="petstore")
public class PetStoreController {
	
	@Autowired
	private PetRepository petRepository;
	
	private String saying;
	public String getSaying() {
		return saying;
	}
	public void setSaying(String saying) {
		this.saying = saying;
	}
	@GetMapping("/{id}")
	public Pet getPetById(@PathVariable Long id){
		
		return petRepository.findOne(id.longValue()).orElseThrow(PetNotFoundException::new);
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pet create(@RequestBody Pet pet){
		
		return petRepository.save(pet);
		
	}
	@DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
		petRepository.findOne(id.longValue()).orElseThrow(PetNotFoundException::new);
		petRepository.deleteById(id);
	}
	@GetMapping
    public Iterable<Pet> findAll() {
        return petRepository.findAll();
    }
	
}
