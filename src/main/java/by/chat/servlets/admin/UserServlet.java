package by.chat.servlets.admin;


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

@WebServlet(name = "UserServlet", urlPatterns = "/api/users")

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
        PrintWriter writer = resp.getWriter();
        List<UserDTO> userDTOS = this.userService.get();

        for (UserDTO userDTO : userDTOS) {
            writer.write(userDTO.getId() + " ");
            writer.write(userDTO.getLogin() + " ");
            writer.write(userDTO.getPassword() + " ");
            writer.write(userDTO.getFirstName() + " ");
            writer.write(userDTO.getMiddleName() + " ");
            writer.write(userDTO.getLastName() + " ");

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String birthDay = null;
            String regDay = null;
            if (userDTO.getBirthday() != null) {
                birthDay = sdf.format(userDTO.getBirthday().getTime());
                regDay = sdf.format(userDTO.getRegistrationDate().getTime());
            } else {
                birthDay = null;
                regDay = null;
            }

            writer.write(birthDay + " ");
            writer.write(regDay + " ");
            writer.write(userDTO.getRole() + "<br>");
        }
    }

}
