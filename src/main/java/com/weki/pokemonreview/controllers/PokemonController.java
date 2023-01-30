package com.weki.pokemonreview.controllers;

import com.weki.pokemonreview.DtoModels.PokemonDto;
import com.weki.pokemonreview.services.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @PostMapping("/pokemons")
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemonDto) {
        PokemonDto pokemon = pokemonService.createPokemon(pokemonDto);
        return new ResponseEntity<>(pokemon, HttpStatus.CREATED);
    }

    @GetMapping("/pokemons")
    public ResponseEntity<List<PokemonDto>> getAllPokemons() {
        List<PokemonDto> pokemons = pokemonService.getAllPokemons();
        return ResponseEntity.ok(pokemons);
    }

    @GetMapping("/pokemons/{id}")
    public ResponseEntity<PokemonDto> getPokemon(@PathVariable("id") Integer pokemonId) {
        PokemonDto pokemon = pokemonService.getPokemon(pokemonId);
        return ResponseEntity.ok(pokemon);
    }

    @DeleteMapping("/pokemons/{id}")
    public void deletePokemon(@PathVariable("id") Integer pokemonId) {
        pokemonService.deletePokemon(pokemonId);
    }

    @PutMapping("/pokemons/{id}")
    public ResponseEntity<PokemonDto> updatePokemon(@PathVariable("id") Integer pokemonId,
                                                    @RequestBody PokemonDto pokemonDto) {
        PokemonDto pokemon = pokemonService.updatePokemon(pokemonId, pokemonDto);
        return new ResponseEntity<>(pokemon, HttpStatus.OK);
    }
}
