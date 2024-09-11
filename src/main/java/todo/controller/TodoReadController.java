package todo.controller;

import javax.servlet.http.Cookie;
import todo.dto.TodoDTO;
import todo.service.TodoService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@WebServlet(name = "todoReadController", urlPatterns = "/todo/read")
@Log4j2
public class TodoReadController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        log.info("todo/read.....실행");
        Long tno = Long.parseLong(req.getParameter("tno"));
        try {
            TodoDTO todoDTO = todoService.get(tno);

            // DB로부터 전달받은 글 하나를 req 에다 저장하기 (담기)
            req.setAttribute("dto", todoDTO);

            // 쿠키 찾기
            Cookie viewTodoCookie = findCookie(req.getCookies(), "viewTodos");
            String todoListStr = viewTodoCookie.getValue();
            boolean isExist = false;

            if (todoListStr != null && todoListStr.indexOf(tno + "-") >= 0) {
                isExist = true;
            }

            log.info("exist " + isExist);

            if (!isExist) {
                todoListStr += tno + "-";
                viewTodoCookie.setValue(todoListStr);
                viewTodoCookie.setMaxAge(60 * 60);
                viewTodoCookie.setPath("/");
                resp.addCookie(viewTodoCookie);
            }
            req.getRequestDispatcher("/todo/read.jsp").forward(req, resp);


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

    private Cookie findCookie(Cookie[] cookies, String cookieName) {
        Cookie targetCookie = null;
        if (cookies != null && cookies.length > 0) {
            for (Cookie ck : cookies) {
                if (ck.getName().equals(cookieName)) {
                    targetCookie = ck;
                    break;
                }
            }
        }
        if (targetCookie == null) {
            targetCookie = new Cookie(cookieName, "");
            targetCookie.setPath("/");
            targetCookie.setMaxAge(60 * 60 * 1);
        }
        return targetCookie;
    }

}

/**
 * 1. 현재 요청 (Request)에 있는 모든 쿠키 중에 조회 목록 쿠키(viewTodos)를 찾아내는 메서드 작성
 * 2. 특정 tno 쿠키의 내용물이 있는지 확인하는 코드 작성
 *
 * doGet viewTodos 이름의 쿠키를 찾고, 쿠키의 내용물을 검사한 후 만일 조회한 적이 없는
 */