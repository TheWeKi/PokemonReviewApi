package com.weki.pokemonreview.controllers;

import com.weki.pokemonreview.DtoModels.ReviewDto;
import com.weki.pokemonreview.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/pokemons/{id}/reviews")
    public ResponseEntity<ReviewDto> createReview(@PathVariable Integer id,
                                                  @RequestBody ReviewDto reviewDto) {
        ReviewDto review = reviewService.createReview(id, reviewDto);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @GetMapping("/pokemons/{id}/reviews")
    public ResponseEntity<List<ReviewDto>> getAllReviews(@PathVariable Integer id) {
        List<ReviewDto> reviews = reviewService.getAllReview(id);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/pokemons/{id}/reviews/{review_id}")
    public ResponseEntity<ReviewDto> getReview(@PathVariable Integer id,
                                                     @PathVariable Integer review_id) {
        ReviewDto review = reviewService.getReview(id, review_id);
        return ResponseEntity.ok(review);
    }

    @PutMapping("/pokemons/{id}/reviews/{review_id}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable Integer id,
                                                  @PathVariable Integer review_id,
                                                  @RequestBody ReviewDto reviewDto) {
        ReviewDto review = reviewService.updateReview(id, review_id, reviewDto);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @DeleteMapping("/pokemons/{id}/reviews/{review_id}")
    public void deleteReview(@PathVariable Integer id, @PathVariable Integer review_id) {
        reviewService.deleteReview(id, review_id);
    }

}
