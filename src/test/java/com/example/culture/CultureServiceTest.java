package com.example.culture;

import com.example.culture.service.CultureService;
import com.example.domain.Culture;
import com.example.domain.PageRequest;
import com.example.domain.PageResponse;
import com.example.util.SingletonProvideUtil;
import org.junit.jupiter.api.Test;

import static com.example.util.SingletonProvideUtil.*;

public class CultureServiceTest {

    @Test
    void getCulturesTest(){
        PageRequest pageRequest = PageRequest.builder()
                .size(5)
                .build();
        CultureService cultureService = SINGLETON_UTIL.cultureService();
        PageResponse<Culture> pageResponse = cultureService.getCultures(pageRequest);
        if(pageResponse.isShowPrev()){
            System.out.println("[PREV]");
        }
        System.out.println(pageResponse.getPageList().size());
        if(pageResponse.isShowNext()){
            System.out.println("[NEXT]");
        }
        System.out.println("pageResponse.getTotal() = " + pageResponse.getTotal());
        System.out.println("pageResponse.getLast() = " + pageResponse.getLast());
        System.out.println("pageResponse.getEnd() = " + pageResponse.getEnd());
    }
}
