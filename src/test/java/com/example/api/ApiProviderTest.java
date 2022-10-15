package com.example.api;

import com.example.service.CultureRegisterServiceImpl;
import com.example.service.RegisterService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class ApiProviderTest {
    ApiProvider apiProvider = new CultureJsonApiProvider
            ("6653645678736b6139317441527257","문화행사");
    RegisterService registerService =
            new CultureRegisterServiceImpl(apiProvider,new CultureApiRatePolicy());

    @Test
    @DisplayName("불러온 데이터의 수 일치")
    void apiProviderTest() throws IOException {
       List<?> list =  apiProvider.apiProvide();
        System.out.println(list.size()==62);
    }

    @Test
    void registerServiceTest(){
        registerService.register();
    }
}
