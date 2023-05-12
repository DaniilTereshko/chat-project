package by.chat.servlets.ui.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet(urlPatterns = "/ui/user/message")
public class UiMessageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf-8");
        HttpSession session = req.getSession();
        String loginError = (String) session.getAttribute("loginError");
        if (loginError!=null){
            req.setAttribute("loginError",loginError);
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ui/user/message.jspx");
        dispatcher.forward(req,resp);

    }
}
