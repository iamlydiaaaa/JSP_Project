package com.example.api;



import com.example.culture.service.CultureService;
import com.example.util.SingletonProvideUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static com.example.util.SingletonProvideUtil.*;

public class ApiProviderTest {
    ApiProvider apiProvider = new CultureJsonApiProvider
            ("6653645678736b6139317441527257","문화행사");

    CultureService cultureService = SINGLETON_UTIL.cultureService();

    @Test
    @DisplayName("불러온 데이터의 수 일치")
    void apiProviderTest() throws IOException {
       List<?> list =  apiProvider.apiProvide();
        Assertions.assertEquals(list.size(),62);
    }

    @Test
    void registerServiceTest(){
        cultureService.register();
    }

    //appconfig 싱글톤 정리 -> 클래스다이어그램
}
