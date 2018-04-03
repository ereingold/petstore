package com.ndgits.petstore.service.repo;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.ndgits.petstore.service.model.Category;
import com.ndgits.petstore.service.model.Pet;

public interface PetRepository extends BaseRepository<Pet>{

	List<Pet> findByCategory(@Param("category") Category category);

}
