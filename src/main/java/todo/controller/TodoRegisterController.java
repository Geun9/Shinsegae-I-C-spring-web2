package todo.controller;

import javax.servlet.http.HttpSession;
import todo.dto.TodoDTO;
import todo.service.TodoService;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@WebServlet(name = "todoRegisterController", urlPatterns = "/todo/register")
@Log4j2
public class TodoRegisterController extends HttpServlet {

    TodoService todoService = TodoService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("입력화면 register.jsp 으로 GET");
        HttpSession session = req.getSession();
        if (session.isNew()) {
            log.info("JSESSIONID 쿠키가 새로 생성된 사용자입니다.");
            resp.sendRedirect("/login");
        }

        if (session.getAttribute("loginInfo") == null) {
            log.info("로그인 정보가 없는 사용자입니다.");
            resp.sendRedirect("/login");
        }

        req.getRequestDispatcher("/todo/register.jsp").forward(req, resp);
    }

    @Override    //
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("입력을 처리하고 목록 페이지 이동");
        TodoDTO todoDTO = TodoDTO.builder()
                .title(req.getParameter("title"))
                .dueDate(LocalDate.parse(req.getParameter("dueDate")))
                .build();

        log.info("/todo/register Post......");
        log.info(todoDTO);

        try {
            todoService.register(todoDTO);
        } catch (Exception e) {
           e.printStackTrace();
        }

        resp.sendRedirect("/todo/list");
    }
}
