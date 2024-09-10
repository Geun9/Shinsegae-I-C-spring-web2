package todo.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import todo.dto.TodoDTO;
import todo.service.TodoService;

@WebServlet(name = "todoListController", urlPatterns = "/todo/list")
@Log4j2
public class TodoListController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        log.info("todo...list 야.....잘 왔어.");

        //code ver 1.0
        //List<TodoDTO> dtoList = TodoService.INSTANCE.getList();
        //req.setAttribute("dtolist",dtoList);

        //req.getRequestDispatcher("/todo/list.jsp").forward(req,resp);

          try {
              List<TodoDTO> dtoList = todoService.listAll();
              req.setAttribute("dtoList",dtoList);
              req.getRequestDispatcher("/todo/list.jsp").forward(req,resp);
          }catch (Exception e) {
             log.error(e.getMessage());
             throw new ServletException("list error");
          }


    }


}
