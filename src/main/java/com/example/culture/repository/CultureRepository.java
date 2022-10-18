package com.example.culture.repository;

import com.example.domain.Culture;
import com.example.domain.PageRequest;
import com.example.domain.PageResponse;

public interface CultureRepository<T> {
    void insert(T t);
    PageResponse<Culture> selectAll(PageRequest pageRequest);
    int selectCount();
}
