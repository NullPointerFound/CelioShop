package com.malik.CelioShop.CelioShop.service.Impl;

import com.malik.CelioShop.CelioShop.entity.review.Review;
import com.malik.CelioShop.CelioShop.entity.review.ReviewVote;
import com.malik.CelioShop.CelioShop.entity.review.VoteType;
import com.malik.CelioShop.CelioShop.entity.user.User;
import com.malik.CelioShop.CelioShop.exception.ResourceNotFound;
import com.malik.CelioShop.CelioShop.playload.VoteResult;
import com.malik.CelioShop.CelioShop.repository.ReviewRepository;
import com.malik.CelioShop.CelioShop.repository.ReviewVoteRepository;
import com.malik.CelioShop.CelioShop.service.ReviewVoteService;
import com.malik.CelioShop.CelioShop.service.ServiceHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ReviewVoteServiceImpl implements ReviewVoteService {

    private ReviewVoteRepository reviewVoteRepository;
    private ReviewRepository reviewRepository;
    private ServiceHelper serviceHelper;

    @Override
    @Transactional
    public VoteResult doVote(VoteType type, Long reviewId) {

        // retrieve the authenticated user
        User user = serviceHelper.getAuthenticatedUser();

        // retrieve the review by the ID
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                ()-> new ResourceNotFound("Review","ID",reviewId)
        );

        // Find out if the user has already voted or not
        Optional<ReviewVote> reviewVote = reviewVoteRepository.findReviewVoteByReviewAndUser(reviewId,user.getId());

        // in case the user already voted
        if(reviewVote.isPresent()){
            if( (reviewVote.get().isDownvoted() && type.equals(VoteType.DOWN)) ||
                    (reviewVote.get().isUpvoted() && type.equals(VoteType.UP))){
                return undoVote(reviewVote.get(),reviewId);
            }
            else if(reviewVote.get().isUpvoted() && type.equals(VoteType.DOWN)){
                reviewVote.get().vote_down();
            }
            else if (reviewVote.get().isDownvoted() && type.equals(VoteType.UP)){
                reviewVote.get().vote_up();
            }
            reviewVoteRepository.save(reviewVote.get());

        }
        // in case the user hasn't voted yet
        else {
            ReviewVote newVote = new ReviewVote();
            newVote.setReview(review);
            newVote.setUser(user);
            if (type.equals(VoteType.UP)){
                newVote.vote_up();
            }
            else if (type.equals(VoteType.DOWN)){
                newVote.vote_down();
            }
            reviewVoteRepository.save(newVote);

        }
        reviewRepository.updateReviewVoteCount(reviewId);
        Integer voteCount = reviewRepository.getVoteCount(reviewId);
        return VoteResult.success("The vote has submitted successfully",voteCount);
    }

    @Override
    public VoteResult undoVote(ReviewVote vote, Long reviewId) {

        reviewVoteRepository.delete(vote);
        reviewRepository.updateReviewVoteCount(reviewId);
        Integer voteCount = reviewRepository.getVoteCount(reviewId);

        return VoteResult.success("Your vote has un-voted successfully",voteCount);
    }
}
