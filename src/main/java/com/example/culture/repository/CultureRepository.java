package com.example.culture.repository;

import com.example.domain.Culture;
import com.example.domain.PageRequest;
import com.example.domain.PageResponse;

import java.util.Optional;

public interface CultureRepository<T> {
    void insert(T t);
    PageResponse<Culture> selectAll(PageRequest pageRequest);
    Optional<Culture> selectOne(Long cno);
    int selectCount();
    void deleteAll();
}
