package com.example.service;

import com.example.api.ApiProvider;
import com.example.api.ApiRatePolicy;
import com.example.domain.Culture;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CultureRegisterServiceImpl implements RegisterService{

    ApiProvider apiProvider;
    ApiRatePolicy apiRatePolicy;

    public CultureRegisterServiceImpl(ApiProvider apiProvider, ApiRatePolicy apiRatePolicy) {
        this.apiProvider = apiProvider;
        this.apiRatePolicy = apiRatePolicy;
    }

    @Override
    public void register() {
        try {
            List<Culture> list = (List<Culture>) apiProvider.apiProvide();
            System.out.println("list = " + list);
            Map<String,Integer> map = apiRatePolicy.apiRatePolicy();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
