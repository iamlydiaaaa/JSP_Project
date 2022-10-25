package com.example.api;


import com.example.culture.service.CultureService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static com.example.common.util.SingletonProvideUtil.SINGLETON_UTIL;

@Slf4j
public class ApiProviderTest {
    ApiProvider apiProvider = new CultureJsonApiProvider
            ("6653645678736b6139317441527257","문화행사");

    CultureService cultureService = SINGLETON_UTIL.cultureService();

    @Test
    @DisplayName("서울시 문화행사 api 연결")
    void apiProviderTest() throws IOException {
       List<?> list =  apiProvider.apiProvide();
       log.info("불러온 데이터의 수 : "+list.size());
       Assertions.assertNotNull(list);
    }

    @Test
    @DisplayName("서울시 문화행사 api db 최신화")
    void registerServiceTest(){
        //삭제하고 최신화 할때 // review cno과 reservation cno가 달라지니 // 같이 삭제해야함
        //아니면 기존 cno 뒤에 붙이고 기간으로 컷해야함
//        cultureService.removeAll();
//        cultureService.register();
    }
}
