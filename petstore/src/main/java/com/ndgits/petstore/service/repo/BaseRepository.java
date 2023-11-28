package com.ndgits.petstore.service.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.ndgits.petstore.service.model.BaseM;

public interface BaseRepository <M extends BaseM> extends JpaRepository<M, Long> {
	List<M> findByName(@Param("name") String name);
}
