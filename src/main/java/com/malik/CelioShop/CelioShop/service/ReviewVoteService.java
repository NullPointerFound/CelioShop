package com.malik.CelioShop.CelioShop.service;

import com.malik.CelioShop.CelioShop.entity.review.ReviewVote;
import com.malik.CelioShop.CelioShop.entity.review.VoteType;
import com.malik.CelioShop.CelioShop.playload.VoteResult;

public interface ReviewVoteService {

    public VoteResult doVote(VoteType type,Long reviewId);
    public VoteResult undoVote(ReviewVote vote, Long reviewId);

}
