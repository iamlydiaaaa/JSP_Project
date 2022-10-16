package com.example.api;



import com.example.config.AppConfig;
import com.example.service.RegisterService;
import com.example.service.SingletonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class ApiProviderTest {
    ApiProvider apiProvider = new CultureJsonApiProvider
            ("6653645678736b6139317441527257","문화행사");

    SingletonService instance = SingletonService.getInstance();
    RegisterService registerService = instance.registerService();

    @Test
    @DisplayName("불러온 데이터의 수 일치")
    void apiProviderTest() throws IOException {
       List<?> list =  apiProvider.apiProvide();
        Assertions.assertEquals(list.size(),62);
    }

    @Test
    void registerServiceTest(){
        registerService.register();
    }

    //appconfig 싱글톤 정리 -> 클래스다이어그램
}
