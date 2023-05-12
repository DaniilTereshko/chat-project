package by.chat.servlets.api;

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

@WebServlet( urlPatterns = "/api/login")
public class LoginServlet extends HttpServlet {
    private static final String LOGIN_PARAM_NAME = "login";
    private static final String PASSWORD_PARAM_NAME = "password";
    private final IUserService userService;

    public LoginServlet() {
        userService = UserServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/ui/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = req.getParameter(LOGIN_PARAM_NAME);
        String password = req.getParameter(PASSWORD_PARAM_NAME);
        UserDTO userDTO = userService.get(login);
        if(userDTO == null){
            req.setAttribute("loginError", "Пользователя с таким логином не существует");
            req.getRequestDispatcher("/ui/login.jsp").forward(req,resp);
        } else if (userDTO.getPassword().equals(password)){
            session.setAttribute("user", userDTO);
        } else {
            req.setAttribute("passwordError", "Неправильный пароль");
            req.getRequestDispatcher("/ui/login.jsp").forward(req,resp);
        }
        resp.sendRedirect("/chat-project-1.0.0/api/home");
    }
}
