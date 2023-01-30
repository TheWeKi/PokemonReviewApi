package com.weki.pokemonreview.DtoModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Integer id;
    private String content;
    private Integer stars;
}
