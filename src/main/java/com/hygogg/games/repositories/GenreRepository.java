package com.hygogg.games.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hygogg.games.models.Genre;


@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {
	
	Optional<Genre> findGenreByName(String name);
	
}
