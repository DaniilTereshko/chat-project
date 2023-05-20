package by.chat.connectors.web.servlets.ui;

import by.chat.core.exception.LoginException;
import by.chat.core.exception.UserException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "UiUserServlet", urlPatterns = "/ui/signUp")
public class UiUserServlet extends HttpServlet {
    private static final String ERROR = "errorCode";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String error = req.getParameter(ERROR);
        int errorCode = 0;
        if(error != null){
            errorCode = Integer.parseInt(error);
        }
        if(errorCode == UserException.EMPTY_LOGIN_ERROR.ordinal()){
            req.setAttribute(ERROR, "Заполните логин");
        }
        if(errorCode == UserException.USER_EXIST.ordinal()){
            req.setAttribute(ERROR, "Пользователь с таким логином уже существует");
        }
        if(errorCode == UserException.EMPTY_PASSWORD_ERROR.ordinal()){
            req.setAttribute(ERROR, "Введите пароль");
        }
        if(errorCode == UserException.PASSWORDS_MATCH_ERROR.ordinal()){
            req.setAttribute(ERROR, "Пароли не совпадают");
        }
        if(errorCode == UserException.EMPTY_FIRSTNAME_ERROR.ordinal()){
            req.setAttribute(ERROR, "Заполните имя");
        }
        if(errorCode == UserException.EMPTY_MIDDLENAME_ERROR.ordinal()){
            req.setAttribute(ERROR, "Заполните отчество");
        }
        if(errorCode == UserException.EMPTY_LASTNAME_ERROR.ordinal()){
            req.setAttribute(ERROR, "Заполните фамилию");
        }
        if(errorCode == UserException.EMPTY_BIRTHDAY_ERROR.ordinal()){
            req.setAttribute(ERROR, "Заполните дату рождения");
        }
        req.getRequestDispatcher("/ui/registration.jspx").forward(req,resp);
    }
}
