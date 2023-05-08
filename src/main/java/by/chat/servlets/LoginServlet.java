package by.chat.servlets;

import by.chat.core.dto.UserDTO;
import by.chat.services.api.IUserService;
import by.chat.services.factory.UserServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private static final String LOGIN_PARAM_NAME = "login";
    private static final String PASSWORD_PARAM_NAME = "password";
    private final IUserService userService;

    public LoginServlet() {
        userService = UserServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = req.getParameter(LOGIN_PARAM_NAME);
        String password = req.getParameter(PASSWORD_PARAM_NAME);
        UserDTO userDTO = userService.get(login);
        if(userDTO == null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Пользователя с таким логином не существует");
        }
        else if (userDTO.getPassword().equals(password)){
            session.setAttribute("user", userDTO);
        }else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неправильный пароль");
        }
    }
}
