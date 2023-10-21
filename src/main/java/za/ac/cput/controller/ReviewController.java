package za.ac.cput.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Review;
import za.ac.cput.dto.ReviewDto;
import za.ac.cput.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    /*@PostMapping("/create")
    public Review create(@RequestBody Review review) {
        return reviewService.create(review);
    }*/

    @PostMapping("/create")
    public ResponseEntity<ReviewDto> createSale(@RequestBody ReviewDto reviewDto) {
        Review createdReview = reviewService.createReview(reviewDto);
        ReviewDto responseDto = reviewService.convertToReviewDto(createdReview);

        if (responseDto != null) {
            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/read/{id}")
    public Review read(@PathVariable Long id) {
        return reviewService.read(id);
    }

    @PutMapping("/update")
    public Review update(@RequestBody Review review) {
        return reviewService.update(review);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id) {
        return reviewService.delete(id);
    }

    @GetMapping("/getAll")
    public List<Review> getAll() {
        return reviewService.getAll();
    }
}
