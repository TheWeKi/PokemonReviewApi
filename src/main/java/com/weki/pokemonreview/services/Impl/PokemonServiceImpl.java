package com.weki.pokemonreview.services.Impl;

import com.weki.pokemonreview.DtoModels.PokemonDto;
import com.weki.pokemonreview.exceptions.EntityNotFoundException;
import com.weki.pokemonreview.models.Pokemon;
import com.weki.pokemonreview.repositories.PokemonRepository;
import com.weki.pokemonreview.services.PokemonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {

    private final PokemonRepository pokemonRepository;

    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    // Pokemon Mappers
    public PokemonDto mapToPokemonDto(Pokemon pokemon) {
        PokemonDto pokemonDto = new PokemonDto();
        pokemonDto.setId(pokemon.getId());
        pokemonDto.setName(pokemon.getName());
        pokemonDto.setType(pokemon.getType());
        return pokemonDto;
    }

    public Pokemon mapToPokemon(PokemonDto pokemonDto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());
        return pokemon;
    }

    //Implementation of all methods
    @Override
    public List<PokemonDto> getAllPokemons() {
        return pokemonRepository.findAll()
                .stream().map( this::mapToPokemonDto )
                .collect(Collectors.toList());
    }

    @Override
    public PokemonDto getPokemon(Integer id) {
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("Pokemon does not exist") );

        return mapToPokemonDto( pokemon );
    }

    @Override
    public PokemonDto createPokemon(PokemonDto pokemonDto) {
        Pokemon savedPokemon = pokemonRepository.save( mapToPokemon(pokemonDto) );
        return mapToPokemonDto(savedPokemon);
    }

    @Override
    public void deletePokemon(Integer id) {
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("Pokemon does not exist") );
        pokemonRepository.delete(pokemon);
    }

    @Override
    public PokemonDto updatePokemon(Integer id, PokemonDto pokemonDto) {
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("Pokemon does not exist") );
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());

        return mapToPokemonDto( pokemonRepository.save(pokemon) );
    }

}
