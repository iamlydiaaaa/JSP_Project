package com.example.qna.dao;

import com.example.qna.vo.QnA_A_VO;
import com.example.qna.vo.QnA_Q_VO;

import java.util.List;

public interface QnADAO {
    /**
     * 고객
     */
    //작성
    Integer insertQnA_Q();
    //목록
    List<QnA_Q_VO> selectALlQnA();
    //검색
    List<QnA_Q_VO> searchQnA(String type,String keyword);
    //수정
    QnA_Q_VO update(Long qqno);
    //삭제
    Integer deleteQnA_Q(Long qqno);

    /**
     * 관리자댓글
     */
    //작성
    Integer insertQnA_A();
    //목록
    List<QnA_A_VO> selectALlQnA(Long qqno);
    //삭제(관리자권한 글목록 삭제)
    //메서드 재활용
    //삭제(댓글 삭제)
    Integer deleteQnA_A(Long qano);
}
