package com.example.review.service;

import com.example.domain.PageRequest;
import com.example.domain.PageResponse;
import com.example.domain.ReviewVO;
import com.example.review.dao.ReviewDAO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewDAO reviewDAO;

    @Override
    public Long writeReview(ReviewVO reviewVO) {
        return reviewDAO.insert(reviewVO);
    }

    @Override
    public PageResponse<ReviewVO> getReviews(Long cno, PageRequest pageRequest) {
        return reviewDAO.selectAll_byCno(cno,pageRequest);
    }

    @Override
    public ReviewVO updateReview(ReviewVO reviewVO) {
        return reviewDAO.update(reviewVO);
    }

    @Override
    public void removeReview(Long re_no) {
        reviewDAO.delete(re_no);
    }

    @Override
    public ReviewVO getReview(Long re_no) {
        return reviewDAO.select(re_no);
    }

    @Override
    public ReviewVO getReview(String id,Long cno) {
        return reviewDAO.select(id,cno);
    }
}
