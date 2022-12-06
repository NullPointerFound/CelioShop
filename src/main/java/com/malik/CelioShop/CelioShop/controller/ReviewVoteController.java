package com.malik.CelioShop.CelioShop.controller;

import com.malik.CelioShop.CelioShop.entity.review.VoteType;
import com.malik.CelioShop.CelioShop.playload.VoteResult;
import com.malik.CelioShop.CelioShop.service.ReviewVoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/product/review")
public class ReviewVoteController {

    private ReviewVoteService reviewVoteService;

    @PostMapping("/{reviewId}/{type}")
    public ResponseEntity<VoteResult> upVote(@PathVariable(name = "reviewId") Long reviewId,
                             @PathVariable(name = "type") String type){

        VoteType voteType = VoteType.valueOf(type.toUpperCase());
        VoteResult voteResult = reviewVoteService.doVote(voteType,reviewId);
        return new ResponseEntity<>(voteResult,HttpStatus.OK);
    }

}
