package todo.controller;

import todo.service.TodoService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@WebServlet(name="todoRemoveController" , value="/todo/remove")
@Log4j2
public class TodoRemoveController extends HttpServlet {

      private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long tno = Long.parseLong(req.getParameter("tno"));
        log.info(tno);

        try {
            todoService.remove(tno);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new ServletException();
        }
            resp.sendRedirect("/todo/list");
    }
}
