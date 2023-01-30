package com.weki.pokemonreview.repositories;

import com.weki.pokemonreview.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {

}
