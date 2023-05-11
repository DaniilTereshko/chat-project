package by.chat.servlets.api.admin;


import by.chat.core.dto.UserDTO;
import by.chat.services.api.IUserService;
import by.chat.services.factory.UserServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = "/api/admin/users")

public class UserServlet extends HttpServlet {
    private static final String LOGIN_PARAM_NAME = "login";
    private static final String PASSWORD_PARAM_NAME = "password";
    private static final String FIRST_NAME_PARAM_NAME = "firstName";
    private static final String MIDDLE_NAME_PARAM_NAME = "middleName";
    private static final String LAST_NAME_PARAM_NAME = "lastName";
    private static final String BIRTHDAY_PARAM_NAME = "birthday";

    private final IUserService userService;

    public UserServlet() {
        this.userService = UserServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        List<UserDTO> userDTOS = userService.get();
        req.setAttribute("users", userDTOS);
        req.getRequestDispatcher("/ui/admin/users.jspx").forward(req,resp);
    }

}
