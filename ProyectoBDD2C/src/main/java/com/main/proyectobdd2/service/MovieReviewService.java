package com.main.proyectobdd2.service;

import com.main.proyectobdd2.model.Review;
import com.main.proyectobdd2.repository.ReviewRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Cacheable(value = "sample_mflix", key = "#movieId")
    public List<Review> getReviewsByMovieId(ObjectId movieId) {
        System.out.println("👉 Entró en getReviewsByMovieId");
        System.out.println("💾 Buscando en MongoDB por movieId: " + movieId);
        return reviewRepository.findByMovieId(movieId);
    }
}