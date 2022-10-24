package com.example.review.controller;

import com.example.domain.PageRequest;
import com.example.domain.PageResponse;
import com.example.domain.ReviewVO;
import com.example.review.service.ReviewService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.example.common.SingletonProvideUtil.SINGLETON_UTIL;

@WebServlet(name="reviewController",value="/review/*")
@Slf4j
public class ReviewController extends HttpServlet {

    private final ReviewService reviewService = SINGLETON_UTIL.reviewService();
    private final Gson gson = SINGLETON_UTIL.gson();

    //자바객체를 json 객체로 응답하는 메서드
    private void sendAsJson(HttpServletResponse response,
                            Object obj) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        String json = gson.toJson(obj);
        System.out.println("json!!!!!!!!!!!!!!!! = " + json);

        PrintWriter out = response.getWriter();

        out.print(json);
        out.flush();
    }

    @Override //get // /review?cno=1&page=1&size=12
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("ReviewController.doGet");
        try {
            Long cno = Long.valueOf(req.getParameter("cno"));
            String paramPage = req.getParameter("page");
            String paramSize = req.getParameter("size");
            PageRequest pageRequest;
            //쿼리스트링으로 받아온 page size를 검증후 그에 맞는 PageRequest생성
            if(paramPage==null&&paramSize==null){
                pageRequest=PageRequest.builder().build();
            }
            else if (paramPage==null||"".equals(paramPage)){
                pageRequest = PageRequest.builder()
                        .size(Integer.parseInt(paramSize))
                        .build();
            }
            else if (paramSize==null|| "".equals(paramSize)) {
                pageRequest = PageRequest.builder()
                        .size(Integer.parseInt(paramPage))
                        .build();
            }
            else{ //paramPage!=null && paramSize!=null
                pageRequest = PageRequest.builder()
                        .page(Integer.parseInt(paramPage))
                        .size(Integer.parseInt(paramSize))
                        .build();
            }
            PageResponse<ReviewVO> pageResponse = reviewService.getReviews(cno,pageRequest);
            //고의 예외발생 테스트
//            throw new Exception("고의 발생 예외");
            sendAsJson(resp,pageResponse);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(404);
            sendAsJson(resp,"WRITE_ERR");
            throw new RuntimeException("잘못된 요청이 들어왔습니다");
        }
    }//review get


}
