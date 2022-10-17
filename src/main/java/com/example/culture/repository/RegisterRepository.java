package com.example.culture.repository;

import com.example.domain.Culture;

public interface RegisterRepository<T> {
    void insert(T t);
}
