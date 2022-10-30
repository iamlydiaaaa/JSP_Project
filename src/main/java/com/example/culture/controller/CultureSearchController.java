package com.example.culture.controller;

import com.example.common.vo.PageRequestVO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="cultureSearchController",value="/cultureSearch")
@Slf4j
public class CultureSearchController extends CultureController{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            log.info("QnASearchController.doGet");
            String keyword = req.getParameter("keyword");
            req.setAttribute("pageResponse",cultureService.searchedGetCultures_noType(keyword, PageRequestVO.builder().build()));
            req.getRequestDispatcher("WEB-INF/view/culture/list.jsp").forward(req,resp);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            resp.sendError(400);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
