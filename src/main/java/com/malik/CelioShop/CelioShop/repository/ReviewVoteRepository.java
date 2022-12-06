package com.malik.CelioShop.CelioShop.repository;

import com.malik.CelioShop.CelioShop.entity.review.ReviewVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReviewVoteRepository extends JpaRepository<ReviewVote,Long> {

    @Query("SELECT v FROM ReviewVote v WHERE v.review.id=?1 AND v.user.id=?2")
    Optional<ReviewVote> findReviewVoteByReviewAndUser(Long reviewId, Long userId);

}
