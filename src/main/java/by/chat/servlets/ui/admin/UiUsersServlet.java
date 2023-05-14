package by.chat.servlets.ui.admin;

import by.chat.core.dto.Role;
import by.chat.core.dto.UserDTO;
import by.chat.core.exception.UsersException;
import by.chat.services.api.IAdminService;
import by.chat.services.api.IUserService;
import by.chat.services.factory.AdminServiceFactory;
import by.chat.services.factory.UserServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UiUsersServlet", urlPatterns = "/ui/admin/users")
public class UiUsersServlet extends HttpServlet {
    private static final String ERROR = "error";
    private final IUserService userService;
    private final IAdminService adminService;

    public UiUsersServlet() {
        this.userService = UserServiceFactory.getInstance();
        this.adminService = AdminServiceFactory.getInstance();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserDTO> userDTOS = userService.get();
        req.setAttribute("users", userDTOS);
        req.setAttribute("roles", Role.values());

        String error = req.getParameter(ERROR);
        if(error != null){
            int errorCode = 0;
            errorCode = Integer.parseInt(error);
            if(errorCode == UsersException.SUCCESS.ordinal()){
                req.setAttribute(ERROR, "Операция прошла успешно");
            }
            if(errorCode == UsersException.ERROR.ordinal()){
                req.setAttribute(ERROR, "Операция не выполнена");
            }
        }

        req.getRequestDispatcher("/ui/admin/users.jsp").forward(req,resp);
    }
}
