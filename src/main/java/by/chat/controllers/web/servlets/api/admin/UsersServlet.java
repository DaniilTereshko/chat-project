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
    private static final String USER_DELETE_ID = "userId";
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter(USER_DELETE_ID);
        HttpSession session = req.getSession();
        String referer = req.getHeader(REFERER_HEADER);
        UserDTO user = (UserDTO) session.getAttribute("user");
        String role = req.getParameter("role");
        int id = Integer.parseInt(req.getParameter("id"));
        UserDTO userDTO = userService.get(id);
        Role dtoRole = userDTO.getRole();
        userService.changeRole(userDTO, role, user);
        if(userId != null){
            int i = Integer.parseInt(userId);
            userService.delete(i);
        }

        int index = referer.indexOf("?");
        String result = (index >= 0) ? referer.substring(0, index) : referer;
        if(!dtoRole.equals(userDTO.getRole())){
            result+="?" + ERROR + "=" + UsersException.SUCCESS.ordinal();
            resp.sendRedirect(result);
        }else {
            result+="?" + ERROR + "="+UsersException.ERROR.ordinal();
            resp.sendRedirect(result);
        }
    }
}
