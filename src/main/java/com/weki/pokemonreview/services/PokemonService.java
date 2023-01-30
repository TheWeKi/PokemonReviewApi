package com.weki.pokemonreview.services;

import com.weki.pokemonreview.DtoModels.PokemonDto;

import java.util.List;

public interface PokemonService {

     List<PokemonDto> getAllPokemons();

     PokemonDto getPokemon(Integer id);

     PokemonDto createPokemon(PokemonDto pokemonDto);

     void deletePokemon(Integer id);

     PokemonDto updatePokemon(Integer id, PokemonDto pokemonDto);

}
