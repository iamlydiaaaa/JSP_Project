package com.example.qna.service;

import com.example.common.vo.PageRequestVO;
import com.example.common.vo.PageResponseVO;
import com.example.qna.vo.QnA_A_VO;
import com.example.qna.vo.QnA_Q_VO;

public interface QnAService {
    /**
     * 공통
     */
    //전체 qna_q 리스트 가져오기
    PageResponseVO<QnA_Q_VO> getQnAQList(PageRequestVO pageRequestVO);

    //개별 qna_q 조회 // 해당 qna_a 가져오기는 ajax호출
    QnA_Q_VO getQnaQ(Long qqno);

    //qna_q 검색
    PageResponseVO<QnA_Q_VO> searchedGetQnAQList(String type,String keyword,PageRequestVO pageRequestVO);

    /**
     * 고객
     */
    //문의작성
    boolean wirteQnAQ(QnA_Q_VO qnaq);
    //수정
    QnA_Q_VO modify(QnA_Q_VO qnaq);
    //삭제
    boolean removeQnAQ(Long qqno);

    /**
     * 관리자
     */
    //답변작성
    boolean wirteQnAA(QnA_A_VO qnaa,String adminId);
    //수정
    QnA_A_VO modify(QnA_A_VO qnaa,String adminId);
    //삭제
    boolean removeQnAA(Long qano,String adminId);
}
