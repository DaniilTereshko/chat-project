package by.chat.controllers.web.servlets.api.admin;


import by.chat.core.dto.Role;
import by.chat.core.dto.UserDTO;
import by.chat.core.enums.UsersException;
import by.chat.services.api.IUserService;
import by.chat.services.factory.UserServiceFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name="UsersServlet",urlPatterns = "/api/admin/users")

public class UsersServlet extends HttpServlet {
    private static final String ERROR = "error";
    private static final String REFERER_HEADER = "Referer";
    private static final String USER_ID = "id";
    private final IUserService userService;

    public UsersServlet() {
        this.userService = UserServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserDTO> userDTOS = userService.get();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(userDTOS);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        String role = req.getParameter("role");
        int id = Integer.parseInt(req.getParameter(USER_ID));
        UserDTO userDTO = userService.get(id);
        Role dtoRole = userDTO.getRole();
        if (!dtoRole.equals(Role.valueOf(role))) {
            userService.changeRole(userDTO, role, user);
            resp.setStatus(HttpServletResponse.SC_OK); // Установить статус 200 OK
            resp.getWriter().write("Role changed");
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Установить статус 200 OK
            resp.getWriter().write("Role not changed");
        }


    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter(USER_ID);
        if (userId != null) {
            int i = Integer.parseInt(userId);
            userService.delete(i);
            resp.setStatus(HttpServletResponse.SC_OK); // Установить статус 200 OK
            resp.getWriter().write("User deleted");
        }
    }
}
