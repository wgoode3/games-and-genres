package com.hygogg.games.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hygogg.games.models.Review;


@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {

}
