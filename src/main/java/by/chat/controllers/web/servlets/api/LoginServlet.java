package by.chat.controllers.web.servlets.api;

import by.chat.core.dto.UserDTO;
import by.chat.core.enums.LoginException;
import by.chat.services.api.IUserService;
import by.chat.services.factory.UserServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/api/login")
public class LoginServlet extends HttpServlet {
    private static final String LOGIN_PARAM_NAME = "login";
    private static final String PASSWORD_PARAM_NAME = "password";
    private static final String REFERER_HEADER = "Referer";
    private static final String ERROR = "errorCode";
    private final IUserService userService;

    public LoginServlet() {
        userService = UserServiceFactory.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String referer = req.getHeader(REFERER_HEADER);
        String login = req.getParameter(LOGIN_PARAM_NAME);
        String password = req.getParameter(PASSWORD_PARAM_NAME);
        UserDTO userDTO = userService.get(login);
        String result = null;
        if(referer!=null) {
            int index = referer.indexOf("?");
            result = (index >= 0) ? referer.substring(0, index) : referer;
        }

        if(userDTO == null){
            result+="?" + ERROR + "=" + LoginException.LOGIN_ERROR.ordinal();
            resp.sendRedirect(result);
        } else if (!userDTO.getPassword().equals(password)){
            result += "?" + ERROR + "=" + LoginException.PASSWORD_ERROR.ordinal();
            resp.sendRedirect(result);
        } else {
            session.setAttribute("user", userDTO);
            resp.sendRedirect(req.getContextPath() + "/ui/");
        }
    }
}
