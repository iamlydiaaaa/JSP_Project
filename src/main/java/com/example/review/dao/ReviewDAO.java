package com.example.review.dao;

import com.example.domain.PageRequest;
import com.example.domain.PageResponse;
import com.example.domain.ReviewVO;

public interface ReviewDAO {
    Long insert(ReviewVO reviewVO);
    PageResponse<ReviewVO> selectAll_byCno(Long cno,PageRequest pageRequest);
    ReviewVO update(ReviewVO reviewVO);
    void delete(Long re_no);
    int selectCount(Long cno);
    ReviewVO select(Long re_no);
    ReviewVO select(String id,Long cno);
}
