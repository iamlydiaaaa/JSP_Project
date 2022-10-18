package com.example.culture.service;

import com.example.domain.Culture;
import com.example.domain.PageRequest;
import com.example.domain.PageResponse;

import java.util.Optional;

public interface CultureService {

    //api 등록 기능
    void register();
    //전체조회 list(limit skip , size)
    PageResponse<Culture> getCultures(PageRequest pageRequest);
    //개별조회
    Culture getCulture(Long cno);
    //수정
    //삭제
    //전부삭제
    void removeAll();
}
