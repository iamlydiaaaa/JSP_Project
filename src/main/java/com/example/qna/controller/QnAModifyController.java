package com.example.qna.controller;

import com.example.qna.vo.QnA_Q_VO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name="qnAModifyController",value="/qnaModify")
@Slf4j
public class QnAModifyController extends QnAController{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("QnAModifyController.doGet");
        HttpSession session = req.getSession();
        try {
            QnA_Q_VO qnaQ = qnAService.getQnaQ(Long.valueOf(req.getParameter("qqno")));
            validateUser(req, session, qnaQ);
            req.setAttribute("qna", qnaQ);
            req.setAttribute("page", req.getParameter("page"));
            req.getRequestDispatcher("WEB-INF/view/qna/qnaModify.jsp").forward(req,resp);
        } catch (NumberFormatException e) {
            e.printStackTrace();
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("QnAModifyController.doPost");
        HttpSession session = req.getSession();
        try {
            QnA_Q_VO qnaQ = qnAService.getQnaQ(Long.valueOf(req.getParameter("qqno")));
            validateUser(req,session,qnaQ);
            qnaQ.setTitle(req.getParameter("title"));
            qnaQ.setContent(req.getParameter("content"));
            qnAService.modify(qnaQ);
            resp.sendRedirect("/project/qnaList?page="+
                    req.getParameter("page"));
        }  catch (NumberFormatException e) {
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