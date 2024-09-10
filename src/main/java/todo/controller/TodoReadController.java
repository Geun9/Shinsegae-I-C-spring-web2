package todo.controller;

import todo.dto.TodoDTO;
import todo.service.TodoService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@WebServlet(name="todoReadController", urlPatterns = "/todo/read")
@Log4j2
public class TodoReadController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("todo/read.....실행");
        Long tno =  Long.parseLong(req.getParameter("tno"));
        try {
            TodoDTO todoDTO = todoService.get(tno);

            // DB로부터 전달받은 글 하나를 req 에다 저장하기 (담기)
            req.setAttribute("dto",todoDTO);

            req.getRequestDispatcher("/todo/read.jsp").forward(req,resp);


        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServletException("read page error");
        }


        //code ver1.0
//        ///todo/read?tno=123
//        Long tno = Long.parseLong(req.getParameter("tno"));
//
//        TodoDTO dto = TodoService.INSTANCE.get(tno);
//
//        req.setAttribute("dto",dto);
//        req.getRequestDispatcher("/todo/read.jsp").forward(req,resp);

    }
}
