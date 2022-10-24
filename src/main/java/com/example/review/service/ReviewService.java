package com.example.review.service;

import com.example.domain.PageRequest;
import com.example.domain.PageResponse;
import com.example.domain.ReviewVO;

public interface ReviewService {
    Long writeReview(ReviewVO reviewVO);
    PageResponse<ReviewVO> getReviews(Long cno, PageRequest pageRequest);
    ReviewVO updateReview(ReviewVO reviewVO);
    void removeReview(Long re_no);
    ReviewVO getReview(Long re_no);
    ReviewVO getReview(String id,Long cno);
}
