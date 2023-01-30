package com.weki.pokemonreview.services.Impl;

import com.weki.pokemonreview.DtoModels.ReviewDto;
import com.weki.pokemonreview.exceptions.EntityNotFoundException;
import com.weki.pokemonreview.models.Pokemon;
import com.weki.pokemonreview.models.Review;
import com.weki.pokemonreview.repositories.PokemonRepository;
import com.weki.pokemonreview.repositories.ReviewRepository;
import com.weki.pokemonreview.services.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final PokemonRepository pokemonRepository;
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(PokemonRepository pokemonRepository, ReviewRepository reviewRepository) {
        this.pokemonRepository = pokemonRepository;
        this.reviewRepository = reviewRepository;
    }

    // Review Mappers
    public Review mapToReview(ReviewDto reviewDto) {
        Review review = new Review();
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());
        return review;
    }

    public ReviewDto mapToReviewDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setContent(review.getContent());
        reviewDto.setStars(review.getStars());
        return reviewDto;
    }


    //Implementation of all methods

    @Override
    public List<ReviewDto> getAllReview(Integer id) {
        pokemonRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Pokemon does not exist")
        );
        return
                reviewRepository.findAllByPokemonId(id)
                .stream().map( this::mapToReviewDto )
                .toList();
    }

    @Override
    public ReviewDto getReview(Integer id, Integer reviewId) {
        pokemonRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Pokemon does not exist")
        );

        // --- Below code only show reviews for specific pokemon ---
       List<Review> reviews = reviewRepository.findAllByPokemonId(id);

        return mapToReviewDto(
                reviews.stream()
                        .filter( review -> review.getId().equals(reviewId) )
                        .findFirst().orElseThrow(
                                () -> new EntityNotFoundException("No Review Exists for given Pokemon")
                        )
        );
       /*
        // Below code can also get any review for any pokemonId
       Review review = reviewRepository.findById(reviewId).orElseThrow(
                                () -> new EntityNotFoundException("No Review Exists for given Pokemon")
                );
       return mapToReviewDto( review ); */
    }

    @Override
    public ReviewDto createReview(Integer id, ReviewDto reviewDto) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Pokemon does not exist")
        );
        Review review = mapToReview(reviewDto);
        review.setPokemon(pokemon);
        Review savedReview = reviewRepository.save(review);
        return mapToReviewDto(savedReview);
    }

    @Override
    public ReviewDto updateReview(Integer id, Integer reviewId, ReviewDto reviewDto) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Pokemon does not exist")
        );
        Review review = mapToReview(reviewDto);
        review.setId(reviewId);
        review.setPokemon(pokemon);
        Review savedReview = reviewRepository.save(review);
        return mapToReviewDto(savedReview);
    }

    @Override
    public void deleteReview(Integer id, Integer reviewId) {
        pokemonRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Pokemon does not exist")
        );
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new EntityNotFoundException("No Review Exists for given Pokemon")
        );
        reviewRepository.delete(review);
    }

}
