package com.weki.pokemonreview.DtoModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokemonDto {
    private Integer id;
    private String name;
    private String type;

}
