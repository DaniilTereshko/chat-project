package by.chat.servlets.api.admin;


import by.chat.core.dto.Role;
import by.chat.core.dto.UserDTO;
import by.chat.services.api.IAdminService;
import by.chat.services.api.IUserService;
import by.chat.services.factory.AdminServiceFactory;
import by.chat.services.factory.UserServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "UserServlet",urlPatterns = "/api/admin/users")

public class UserServlet extends HttpServlet {
    private static final String LOGIN_PARAM_NAME = "login";
    private static final String PASSWORD_PARAM_NAME = "password";
    private static final String FIRST_NAME_PARAM_NAME = "firstName";
    private static final String MIDDLE_NAME_PARAM_NAME = "middleName";
    private static final String LAST_NAME_PARAM_NAME = "lastName";
    private static final String BIRTHDAY_PARAM_NAME = "birthday";

    private final IUserService userService;
    private final IAdminService adminService;

    public UserServlet() {
        this.userService = UserServiceFactory.getInstance();
        this.adminService = AdminServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        List<UserDTO> userDTOS = userService.get();
        req.setAttribute("users", userDTOS);
        req.setAttribute("roles", Role.values());
        req.getRequestDispatcher("/ui/admin/users.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        String role = req.getParameter("role");
        int id = Integer.parseInt(req.getParameter("id"));
        UserDTO userDTO = userService.get(id);
        Role dtoRole = userDTO.getRole();
        adminService.changeRole(userDTO, role, user);
        if(!dtoRole.equals(userDTO.getRole())){
            req.setAttribute("ok", "Операция прошла успешно");
        }else {
            req.setAttribute("error", "Ошибка выполнения");
        }
        doGet(req,resp);
        //resp.sendRedirect(req.getContextPath() + "/api/admin/users");
    }
}
