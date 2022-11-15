package com.malik.CelioShop.CelioShop.entity.review;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ReviewVote {

    private static final int vote_up = 1;
    private static final int vote_down = -1;

    @Id
    private Long id;

    private Integer vote;

    //@ManyToOne
    //private User user_id;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;


    public void vote_up(){
        this.vote =  vote_up;
    }
    public void vote_down(){
        this.vote = vote_down;
    }
}
