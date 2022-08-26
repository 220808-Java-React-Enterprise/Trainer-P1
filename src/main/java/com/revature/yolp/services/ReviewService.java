package com.revature.yolp.services;

import com.revature.yolp.daos.ReviewDAO;
import com.revature.yolp.models.Review;

import java.util.List;

public class ReviewService {
    private final ReviewDAO reviewDAO;

    public ReviewService(ReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    public void saveReview(Review review) {
        reviewDAO.save(review);
    }

    public List<Review> getAllReviewsByRestaurantId(String id) {
        return reviewDAO.getAllByRestaurantId(id);
    }
}
