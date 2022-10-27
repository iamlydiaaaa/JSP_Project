package com.example.qna.dao;

import com.example.qna.vo.QnA_A_VO;
import com.example.qna.vo.QnA_Q_VO;

import java.util.List;

public class JdbcQnADAO implements QnADAO{
    @Override
    public Integer insertQnA_Q() {
        return null;
    }

    @Override
    public List<QnA_Q_VO> selectALlQnA() {
        return null;
    }

    @Override
    public List<QnA_Q_VO> searchQnA(String type, String keyword) {
        return null;
    }

    @Override
    public QnA_Q_VO update(Long qqno) {
        return null;
    }

    @Override
    public Integer deleteQnA_Q(Long qqno) {
        return null;
    }

    @Override
    public Integer insertQnA_A() {
        return null;
    }

    @Override
    public List<QnA_A_VO> selectALlQnA(Long qqno) {
        return null;
    }

    @Override
    public Integer deleteQnA_A(Long qano) {
        return null;
    }
}
