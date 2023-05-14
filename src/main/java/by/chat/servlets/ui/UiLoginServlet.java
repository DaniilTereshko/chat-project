package by.chat.servlets.ui;

import by.chat.core.exception.LoginException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(name = "UiLoginServlet", urlPatterns = "/ui/signIn")
public class UiLoginServlet extends HttpServlet {
    private static final String ERROR = "errorCode";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String error = req.getParameter(ERROR);
        int errorCode = 0;
        if(error != null){
            errorCode = Integer.parseInt(error);
        }
        if(errorCode == LoginException.LOGIN_ERROR.ordinal()){
            req.setAttribute(ERROR, "Пользователя с таким логином не существует");
        }
        if(errorCode == LoginException.PASSWORD_ERROR.ordinal()){
            req.setAttribute(ERROR, "Неверный пароль");
        }
        req.getRequestDispatcher("/ui/login.jsp").forward(req,resp);
    }

}
