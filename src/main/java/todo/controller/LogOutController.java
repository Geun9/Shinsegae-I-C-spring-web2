package todo.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

@WebServlet(name = "login", urlPatterns = "/login")
@Log4j2
public class LoginController extends HttpServlet {
    // get은 로그인 화면을 보여주고, POST 방식으로 실제 로그인 처리하도록 구성하도록 한다.
    // 1. webservlet 해당 컨트롤러 등록
    // 2. doGet으로 login.jsp 파일로 포워딩
    // 3. login.jsp 파일 만들기


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        log.info("로그인 페이지.. Get");
        req.getRequestDispatcher("/todo/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        log.info("로그인 페이지... Post");
        String id = req.getParameter("id");
        String pw = req.getParameter("w");

        String str = id + pw;

        HttpSession session = req.getSession();
        session.setAttribute("loginInfo", str);
        resp.sendRedirect("/todo/list");
    }
}
