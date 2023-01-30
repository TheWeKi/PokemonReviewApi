package com.weki.pokemonreview.repositories;

import com.weki.pokemonreview.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findAllByPokemonId(Integer id); // reviews by foreign key that is pokemon_id
}
