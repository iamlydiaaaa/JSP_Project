package com.example.qna.controller;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name="qnADeleteController",value="/qnaDelete")
@Slf4j
public class QnADeleteController extends QnAController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("QnADeleteController.doGet");
        try {
            Long qqno = Long.valueOf(req.getParameter("qqno"));
            validateUser(req,req.getSession(),qnAService.getQnaQ(qqno));
            qnAService.removeQnAQ(qqno);
            resp.sendRedirect("/project/qnaList?page="+
                    req.getParameter("page"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            resp.setStatus(400);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            resp.sendRedirect("/project/qnaList?page="+
                    req.getParameter("page")+"&msg="+
                    URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
