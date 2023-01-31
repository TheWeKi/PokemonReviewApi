package com.weki.pokemonreview.DtoModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntityDto {
    private Integer id;
    private String username;
    private String password;
}
