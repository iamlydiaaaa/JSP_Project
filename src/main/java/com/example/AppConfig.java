package com.example;


import com.example.api.ApiProvider;
import com.example.api.ApiRatePolicy;
import com.example.api.CultureJsonApiProvider;
import com.example.api.ProjectApiRatePolicy;
import com.example.culture.dao.CultureDAO;
import com.example.culture.dao.JdbcCultureDAO;
import com.example.culture.service.CultureService;
import com.example.culture.service.CultureServiceImpl;
import com.example.domain.CultureVO;
import com.example.user.dao.JdbcUserDAO;
import com.example.user.dao.UserDAO;
import com.example.user.service.UserService;
import com.example.user.service.UserServiceImpl;

import javax.sql.DataSource;

public class AppConfig {

    ///////////////////////ds

    //dataSource
    public DataSource dataSource() {
        return new HikariDsConfig().config();
    }

    ///////////////////////user

    //userService
    public UserService userService() {
        return new UserServiceImpl(userRepository());
    }

    //userRepository
    public UserDAO userRepository() {
        return new JdbcUserDAO();
    }


    ///////////////////////api

    //apiProvider
    public ApiProvider apiProvider(){
        return new CultureJsonApiProvider
                ("6653645678736b6139317441527257","문화행사");
    }

    //apiRatePolicy
    public ApiRatePolicy apiRatePolicy(){
        return new ProjectApiRatePolicy();
    }

    ///////////////////////culture

    //cultureService
    public CultureService cultureService(){
            return new CultureServiceImpl(apiProvider(),apiRatePolicy(),cultureRepository());
    }

    //cultureRepository
    public CultureDAO<CultureVO> cultureRepository() {
        return new JdbcCultureDAO();
    }

}
