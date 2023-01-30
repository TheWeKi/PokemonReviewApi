package com.weki.pokemonreview.services;

import com.weki.pokemonreview.DtoModels.ReviewDto;

import java.util.List;

public interface ReviewService {

    List<ReviewDto> getAllReview(Integer id);
    ReviewDto getReview(Integer id, Integer reviewId);
    ReviewDto createReview(Integer id, ReviewDto reviewDto);
    ReviewDto updateReview(Integer id, Integer reviewId, ReviewDto reviewDto);
    void deleteReview(Integer id, Integer reviewId);
}
