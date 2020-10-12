package com.hygogg.games.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hygogg.games.models.Game;


@Repository
public interface GameRepository extends CrudRepository<Game, Long> {}
