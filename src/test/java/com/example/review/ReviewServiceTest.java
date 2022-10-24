package com.example.review;



import com.example.domain.PageRequest;
import com.example.domain.PageResponse;
import com.example.domain.ReviewVO;
import com.example.review.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.example.common.ConnectionUtil.CONN_UTIL;
import static com.example.common.SingletonProvideUtil.*;

@Slf4j
public class ReviewServiceTest {

    private final ReviewService reviewService = SINGLETON_UTIL.reviewService();

    @Test
    @DisplayName("리뷰 작성")
    public void writeReviewTest() throws Exception {
        //given
        Long cno = 1L;
        String id = "user1";
        int count = 0;
        //when
        ReviewVO reviewVO = ReviewVO.builder()
                .id(id)
                .cno(cno)
                .content("content")
                .grade((int)(Math.random()*5+1))
                .build();
        Long result = reviewService.writeReview(reviewVO);
        //then
        Assertions.assertEquals(1L,result);
    }
    
    @Test
    @DisplayName("리뷰목록 가져오기")
    public void readReviewsTest() throws Exception {
        //given
        Long cno = 1L;
        PageRequest pageRequest = PageRequest.builder().build();
        log.info("page = "+pageRequest.getPage());
        log.info("size = "+pageRequest.getSize());
        //when
        PageResponse<ReviewVO> pageResponse =
                reviewService.getReviews(cno,pageRequest);
        //then
        Assertions.assertNotNull(pageResponse);

        boolean showPrev = pageResponse.isShowPrev();
        System.out.println("showPrev = " + showPrev);
        List<ReviewVO> reviewVOS = pageResponse.getPageList();
        System.out.println("reviewVOS = " + reviewVOS);
        boolean showNext = pageResponse.isShowNext();
        System.out.println("showNext = " + showNext);
        int start = pageResponse.getStart();
        System.out.println("start = " + start);
        int end = pageResponse.getEnd();
        System.out.println("end = " + end);
        int last = pageResponse.getLast();
        System.out.println("last = " + last);
        int totalCnt = pageResponse.getTotal();
        System.out.println("totalCnt = " + totalCnt);
    }

    @Test
    @DisplayName("리뷰 업데이트 테스트")
    public void updateReviewTest(){
        //given
        Long re_no = 1L;
        String updatedContent = "updated Content";
        ReviewVO reviewVO1 = reviewService.getReview(re_no);
        reviewVO1.setContent(updatedContent);
        System.out.println("reviewVO1 = " + reviewVO1);
        //when
        ReviewVO reviewVO2 = reviewService.updateReview(reviewVO1);
        System.out.println("reviewVO2 = " + reviewVO2);
        //then
        Assertions.assertEquals(reviewVO2.getContent(),updatedContent);
    }

    @Test
    @DisplayName("리뷰 삭제 테스트")
    public void deleteReviewTest() throws Exception {
        Long re_no = 1L;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "delete\n" +
                "from review\n" +
                "where re_no = ?";
        conn = CONN_UTIL.getConnection();
        conn.setAutoCommit(false);
        pstmt = conn.prepareStatement(sql);
        pstmt.setLong(1,re_no);
        int rowCnt = pstmt.executeUpdate();
        Assertions.assertEquals(1,rowCnt);
        conn.rollback();
        conn.setAutoCommit(true);
        CONN_UTIL.close(pstmt,conn);
    }
}
