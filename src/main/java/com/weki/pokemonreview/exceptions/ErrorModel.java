package com.weki.pokemonreview.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorModel {
    private Integer statusCode;
    private String message;
    private Date timestamp;
}
