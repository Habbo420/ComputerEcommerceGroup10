package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Enquiry;
import za.ac.cput.domain.Review;
import za.ac.cput.domain.User;
import za.ac.cput.dto.EnquiryDto;
import za.ac.cput.dto.ReviewDto;
import za.ac.cput.dto.UserDto;
import za.ac.cput.repository.ReviewRepository;
import za.ac.cput.repository.UserRepository;
import za.ac.cput.service.ReviewService;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository repository;

    private UserRepository userRepository;

    @Autowired
    private ReviewServiceImpl(ReviewRepository repository, UserRepository userRepository){
        this.repository = repository;
        this.userRepository = userRepository;
    }
    @Override
    public Review create(Review review) {
        return this.repository.save(review);
    }

    @Override
    public Review read(Long reviewID) {
        return this.repository.findById(reviewID).orElse(null);
    }

    @Override
    public Review update(Review review) {
        if(this.repository.existsById(review.getReviewID()))
            return this.repository.save(review);
        return null;
    }

    @Override
    public boolean delete(Long id) {
        if(this.repository.existsById(id)){
            this.repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Review> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Review createReview(ReviewDto reviewDto) {
        Review review = convertToEntity(reviewDto);

        return repository.save(review);
    }


    public Review convertToEntity(ReviewDto reviewDto) {
        Review review = new Review();
        review.setReviewID(reviewDto.getReviewID());
        review.setProduct(reviewDto.getProduct());
        review.setRating(reviewDto.getRating());

        UserDto customerDto = reviewDto.getCustomer();
        User customer = userRepository.getReferenceById(customerDto.getCustomerID()); // Used userRepository
        review.setCustomer(customer);

        if (customer != null) {
            review.setCustomer(customer);
        } else {
            System.out.println("Could not find user!");
        }

        return review;
    }

    @Override
    public ReviewDto convertToReviewDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setReviewID(reviewDto.getReviewID());
        reviewDto.setProduct(review.getProduct());
        reviewDto.setRating(review.getRating());

        return reviewDto;
    }
}
