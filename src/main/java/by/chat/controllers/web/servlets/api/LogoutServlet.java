package by.chat.controllers.web.servlets.api;

import by.chat.core.dto.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LogoutServlet", urlPatterns = "/api/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contextPath = req.getContextPath();
        HttpSession session = req.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        if(user != null){
            session.removeAttribute("user");
            session.invalidate();
        }
        resp.sendRedirect(contextPath + "/ui/signIn");
    }
}
