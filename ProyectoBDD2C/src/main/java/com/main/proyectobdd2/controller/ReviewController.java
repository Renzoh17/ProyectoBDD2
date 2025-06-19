package com.main.proyectobdd2.controller;


import com.main.proyectobdd2.model.Review;
import com.main.proyectobdd2.service.ReviewService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> save(@RequestBody Review review){
        return new ResponseEntity<>(reviewService.save(review), HttpStatus.CREATED);
    }

    // ✅ Este es el método clásico que espera la UI
    @GetMapping("/one/{id}")
    public ResponseEntity<Review> getOne(@PathVariable ObjectId id) {
        Review review = reviewService.get(id);
        if (review == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    // ✅ Este es el método nuevo que devuelve todas las reviews de una película
    @GetMapping("/movie-by-review/{id}")
    public ResponseEntity<List<Review>> getReviewsByReviewId(@PathVariable ObjectId id) {
        List<Review> reviews = reviewService.getReviewsByReviewId(id);
        if (reviews.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Review> update(@PathVariable ObjectId id, @RequestBody Review review){
        return new ResponseEntity<>(reviewService.update(id, review), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable ObjectId id){
        reviewService.delete(id);
        return new ResponseEntity<>("Review eliminada!", HttpStatus.OK);
    }
}
