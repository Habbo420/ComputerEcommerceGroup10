package za.ac.cput.service;



import za.ac.cput.domain.Review;
import za.ac.cput.dto.ReviewDto;

import java.util.List;

public interface ReviewService extends IService<Review, Long>{
    List<Review> getAll();

    Review createReview(ReviewDto reviewDto);

    ReviewDto convertToReviewDto(Review review);
}
