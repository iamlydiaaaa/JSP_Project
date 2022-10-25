package jk.test;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Servlet", value = "/Servlet/*")
public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html:charset=utf-8");
        String action = request.getPathInfo();

        if(action.equals("/calendar")){
            String y = request.getParameter("sel_y");
            String m = request.getParameter("sel_m");
            String d = request.getParameter("sel_d");

            System.out.println(y + " / " + m + " / " + d);
        }




    }
}
