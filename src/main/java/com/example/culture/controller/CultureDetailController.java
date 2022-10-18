package com.example.culture.controller;

import com.example.domain.Culture;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name="cultureDetailController",value="/detail")
public class CultureDetailController extends CultureController{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CultureDetailController.doGet");
        //1. 클릭한 목록의 cno를 가져와 db에서 조회
        //2. request영역에 저장후 detail.jsp로 전달
        @Valid Long cno = Long.valueOf(req.getParameter("cno"));
        System.out.println("cno = " + cno);
        try {
            req.setAttribute("culture",cultureService.getCulture(cno));
            req.getRequestDispatcher("detail.jsp").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("culture 조회에 실패했습니다");
            String msg = URLEncoder.encode("다시 조회해 주세요", StandardCharsets.UTF_8);
            resp.sendRedirect("/project/list="+msg);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CultureDetailController.doPost");
        super.doPost(req, resp);
    }
}
