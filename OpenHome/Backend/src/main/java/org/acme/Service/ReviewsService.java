package org.acme.Service;

import org.acme.entity.Reviews;
import org.acme.repository.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class ReviewsService {

    @Autowired
    ReviewRepo reviewRepo;

    public float getAverageReviews(int id) {

        Reviews review=reviewRepo.findByPersonId(id);
        if(review==null){
            return 0;
        }
        else{
            float avg=(float)review.getTotalRating()/(float)review.getReviewedByCount();
            return avg;
        }
    }

    public void addReview(int id,int rating) {

        Reviews review=reviewRepo.findByPersonId(id);
        if(review==null){
            review=new Reviews(id,rating,1);

            reviewRepo.save(review);
        }
        else{
            review.setTotalRating(review.getTotalRating()+rating);
            review.setReviewedByCount(review.getReviewedByCount()+1);
        }
    }
}
