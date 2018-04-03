package com.ndgits.petstore.service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ndgits.petstore.service.model.BaseM;

public interface BaseRepository <M extends BaseM> extends PagingAndSortingRepository<M, Long> {
	List<M> findByName(@Param("name") String name);
	Optional<M> findOne(long id);
}
