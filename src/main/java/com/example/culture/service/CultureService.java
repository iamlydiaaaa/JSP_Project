package com.example.culture.service;

import com.example.domain.Culture;
import com.example.domain.PageRequest;
import com.example.domain.PageResponse;

public interface CultureService {

    //api 등록 기능
    void register();

    //list(limit skip , size)
    PageResponse<Culture> getCultures(PageRequest pageRequest);

    //조회
    Culture getCulture(Long cno);

    //수정
    //삭제
}
