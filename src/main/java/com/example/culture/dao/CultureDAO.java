package com.example.culture.dao;

import com.example.culture.vo.CultureVO;
import com.example.common.vo.PageRequestVO;
import com.example.common.vo.PageResponseVO;

import java.util.Optional;

public interface CultureDAO<T> {
    void insert(T t);
    PageResponseVO<CultureVO> selectAll(PageRequestVO pageRequestVO);
    Optional<CultureVO> selectOne(Long cno);
    int selectCount();
    void deleteAll();
}
