package by.chat.controllers.web.servlets.ui;

import by.chat.core.enums.UserException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "UiUserServlet", urlPatterns = "/ui/signUp")
public class UiUserServlet extends HttpServlet {
    private static final String ERROR = "errorCode";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String error = req.getParameter(ERROR);
        HttpSession session = req.getSession();
        Map<UserException, String> exceptions = null;
        int errorCode = 0;
        if(error != null){
            errorCode = Integer.parseInt(error);
        }
        if(errorCode == UserException.LOGIN_ERROR.ordinal()){
            req.setAttribute(ERROR, "Заполните логин");
        }
        if(errorCode == UserException.USER_EXIST.ordinal()){
            req.setAttribute(ERROR, "Пользователь с таким логином уже существует");
        }
        if(errorCode == UserException.PASSWORD_ERROR.ordinal()){
            req.setAttribute(ERROR, "Введите пароль");
        }
        if(errorCode == UserException.PASSWORDS_MATCH_ERROR.ordinal()){
            req.setAttribute(ERROR, "Пароли не совпадают");
        }
        if(errorCode == UserException.FIRSTNAME_ERROR.ordinal()){
            req.setAttribute(ERROR, "Заполните имя");
        }
        if(errorCode == UserException.MIDDLENAME_ERROR.ordinal()){
            req.setAttribute(ERROR, "Заполните отчество");
        }
        if(errorCode == UserException.LASTNAME_ERROR.ordinal()){
            req.setAttribute(ERROR, "Заполните фамилию");
        }
        if(errorCode == UserException.BIRTHDAY_ERROR.ordinal()){
            req.setAttribute(ERROR, "Заполните дату рождения");
        }
        exceptions = (Map<UserException, String>) session.getAttribute("errors");
        if (exceptions != null) {
            for (UserException e : exceptions.keySet()) {
                String s = exceptions.get(e);
                req.setAttribute(e.name().toLowerCase(), s);
            }
            session.removeAttribute("errors");
        }
        req.getRequestDispatcher("/ui/registration.jspx").forward(req,resp);
    }
}
