package com.example.qna.dao;

import com.example.qna.vo.QnA_Q_VO;

import java.util.List;

public interface QnADAO {
    /**
     * 고객
     */
    //작성
    void insert();
    //목록
    List<QnA_Q_VO> selectALlQnA();
    //검색
    List<QnA_Q_VO> searchQnA(String type,String keyword);
    //수정

    //삭제


    /**
     * 관리자댓글
     */
    //작성

    //목록

    //삭제(관리자권한 글목록 삭제)

    //삭제(댓글 삭제)
}
