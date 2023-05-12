package by.chat.servlets.ui;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(name = "UiLoginServlet", urlPatterns = "/ui/signIn")
public class LoginServlet extends HttpServlet {
    private static final String LOGIN_ERROR = "loginError";
    private static final String PASSWORD_ERROR = "passwordError";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loginError = req.getParameter(LOGIN_ERROR);
        String passwordError = req.getParameter(PASSWORD_ERROR);
        if(loginError != null){
            req.setAttribute(LOGIN_ERROR, loginError);
        }
        if(passwordError != null){
            req.setAttribute(PASSWORD_ERROR, passwordError);
        }
        req.getRequestDispatcher("/ui/login.jsp").forward(req,resp);
    }

}
