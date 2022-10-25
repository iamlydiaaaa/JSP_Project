package com.example.culture.service;

import com.example.api.ApiProvider;
import com.example.api.ApiRatePolicy;
import com.example.common.vo.PageRequestVO;
import com.example.common.vo.PageResponseVO;
import com.example.culture.dao.CultureDAO;
import com.example.culture.vo.CultureVO;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class CultureServiceImpl implements CultureService {

    private final ApiProvider apiProvider;
    private final ApiRatePolicy apiRatePolicy;
    private final CultureDAO<CultureVO> cultureDAO;

//    public CultureServiceImpl(ApiProvider apiProvider,
//              ApiRatePolicy apiRatePolicy ,
//              CultureRepository<Culture> cultureRepository) {
//        this.apiProvider = apiProvider;
//        this.apiRatePolicy = apiRatePolicy;
//        this.cultureRepository = cultureRepository;
//    }

    @Override
    public void register() {
        try {
            //가져온 api 데이터로 culture 객체를 완성 후 insert
            List<CultureVO> list = (List<CultureVO>) apiProvider.apiProvide();
            for (CultureVO cultureVO : list) {
                Map<String,Integer> map = apiRatePolicy.apiRatePolicy();
                cultureVO.setCapacity(map.get("capacity"));
                if(cultureVO.getPay_ay_nm().contains("유료")){
                    cultureVO.setPrice(map.get("price"));
                }
                else{
                    cultureVO.setPrice(0);
                }
                //repository에 insert
                cultureDAO.insert(cultureVO);
            }//for

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("api 등록에 실패했습니다");
            throw new RuntimeException(e);
        }
    }

    @Override
    public PageResponseVO<CultureVO> getCultures(PageRequestVO pageRequestVO) {
        return cultureDAO.selectAll(pageRequestVO);
    }

    @Override
    public CultureVO getCulture(Long cno) {
        Optional<CultureVO> result = cultureDAO.selectOne(cno);
        return result.orElseThrow(); //NoSuchElementException
    }

    @Override
    public void removeAll() {
        cultureDAO.deleteAll();
    }
}
