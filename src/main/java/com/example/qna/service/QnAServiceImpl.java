package com.example.qna.service;

import com.example.common.util.MyTransactional;
import com.example.common.vo.PageRequestVO;
import com.example.common.vo.PageResponseVO;
import com.example.qna.dao.QnADAO;
import com.example.qna.vo.QnA_A_VO;
import com.example.qna.vo.QnA_Q_VO;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;

import static com.example.common.util.ConnectionUtil.CONN_UTIL;

@RequiredArgsConstructor
public class QnAServiceImpl implements QnAService {

    private final QnADAO qnADAO;

    /**
     * qna 목록 조회
     */
    @Override
    public PageResponseVO<QnA_Q_VO> getQnAQList(PageRequestVO pageRequestVO) {
        return qnADAO.selectAllQnAQ(pageRequestVO);
    }

    /**
     * qna 개별 조회
     */
    @Override
    @MyTransactional
    public QnA_Q_VO getQnaQ(Long qqno) {
        Connection conn = null;
        try {
            conn = CONN_UTIL.getConnection();

            ///////////
            conn.setAutoCommit(false);
            ///////////

            QnA_Q_VO qnaq = qnADAO.selectOne(qqno,conn);
            qnaq.setCnt(qnaq.getCnt()+1);
            qnADAO.updateQnAQ(qnaq,conn);

            ////////////
            conn.commit();
            conn.setAutoCommit(true);
            ////////////

            return qnaq;
        } catch (Exception e) {

            /////////////////
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException("롤백중 예외 발생");
            }
            ////////////////

            throw new RuntimeException("rollback");
        } finally {
            CONN_UTIL.close(conn);
        }
    }

    /**
     * qna 조회
     */
    @Override
    public PageResponseVO<QnA_A_VO> getQnAAList(Long qqno, PageRequestVO pageRequestVO) {
        return qnADAO.selectAllQnAA(qqno,pageRequestVO);
    }

    /**
     * qna 검색
     */
    @Override
    public PageResponseVO<QnA_Q_VO> searchedGetQnAQList(String type, String keyword, PageRequestVO pageRequestVO) {
        return qnADAO.searchQnA(pageRequestVO,type,keyword);
    }

    /**
     * qna 작성
     */
    @Override
    public boolean writeQnAQ(QnA_Q_VO qnaq) {
        return qnADAO.insertQnA_Q(qnaq)==1;
    }

    /**
     * qna 수정
     */
    @Override
    public QnA_Q_VO modify(QnA_Q_VO qnaq) {
        return qnADAO.updateQnAQ(qnaq);
    }

    /**
     * qna 삭제
     */
    @Override
    public boolean removeQnAQ(Long qqno) {
        return qnADAO.deleteQnA_Q(qqno)==1;
    }

    /**
     * qna 댓글 작성
     */
    @Override
    public boolean writeQnAA(QnA_A_VO qnaa) {
        return qnADAO.insertQnA_A(qnaa)==1;
    }

    /**
     * qna 댓글 수정
     */
    @Override
    public QnA_A_VO modify(QnA_A_VO qnaa) {
        return qnADAO.updateQnAA(qnaa);
    }

    /**
     * qna 댓글 삭제
     */
    @Override
    public boolean removeQnAA(Long qano) {
        return qnADAO.deleteQnA_A(qano)==1;
    }

    @Override
    public QnA_A_VO getQnAA(Long qano) {
        return qnADAO.selectOneQnAA(qano);
    }
}
