package com.example.api;

import java.util.Map;

public interface ApiRatePolicy {
    //capacity[0] price[1]
    Map<String,Integer> apiRatePolicy();
}
