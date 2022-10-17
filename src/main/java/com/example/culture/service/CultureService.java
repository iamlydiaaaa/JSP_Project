package com.example.culture.service;

import com.example.domain.Culture;
import com.example.domain.PageRequest;
import com.example.domain.PageResponse;

public interface CultureService {
    //api 등록 기능
    void register();
    PageResponse<Culture> getCultures(PageRequest pageRequest);
    //조회
    //수정
    //삭제
}
