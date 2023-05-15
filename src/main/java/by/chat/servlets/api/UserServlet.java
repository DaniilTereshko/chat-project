package by.chat.servlets.api;


import by.chat.core.dto.Role;
import by.chat.core.dto.UserCreateDTO;
import by.chat.core.dto.UserDTO;
import by.chat.core.exception.LoginException;
import by.chat.core.exception.UserException;
import by.chat.services.api.IUserService;
import by.chat.services.factory.UserServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@WebServlet(name = "UserServlet", urlPatterns = "/api/user")

public class UserServlet extends HttpServlet {
    private static final String LOGIN_PARAM_NAME = "login";
    private static final String PASSWORD_PARAM_NAME = "password";
    private static final String CONFIRMED_PASSWORD_PARAM_NAME = "confirmed_password";
    private static final String FIRST_NAME_PARAM_NAME = "firstName";
    private static final String MIDDLE_NAME_PARAM_NAME = "middleName";
    private static final String LAST_NAME_PARAM_NAME = "lastName";
    private static final String BIRTHDAY_PARAM_NAME = "birthday";
    private static final String REFERER_HEADER = "Referer";
    private static final String ERROR = "errorCode";

    private final IUserService userService;

    public UserServlet() {
        this.userService = UserServiceFactory.getInstance();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String referer = req.getHeader(REFERER_HEADER);
        HttpSession session = req.getSession();
        // палучаем параметры
        Date birthday_date = null;
        String login = req.getParameter(LOGIN_PARAM_NAME);
        String password = req.getParameter(PASSWORD_PARAM_NAME);
        String confirmedPassword = req.getParameter(CONFIRMED_PASSWORD_PARAM_NAME);
        String firstName = req.getParameter(FIRST_NAME_PARAM_NAME);
        String middleName = req.getParameter(MIDDLE_NAME_PARAM_NAME);
        String lastName = req.getParameter(LAST_NAME_PARAM_NAME);
        String birthday = req.getParameter(BIRTHDAY_PARAM_NAME);

        String result = null;
        if(referer!=null) {
            int index = referer.indexOf("?");
            result = (index >= 0) ? referer.substring(0, index) : referer;
        }
        // проверяем на совпадение такого user по логину
        // проверяем на заполнение полей
        if ((login == null) || (login.equals(""))) {
            result+="?" + ERROR + "=" + UserException.EMPTY_LOGIN_ERROR.ordinal();
            resp.sendRedirect(result);
        } else if(userService.get(login) != null){
            result+="?" + ERROR + "=" + UserException.USER_EXIST.ordinal();
            resp.sendRedirect(result);
        } else if((password == null) || (password.equals(""))){
            result+="?" + ERROR + "=" + UserException.EMPTY_PASSWORD_ERROR.ordinal();
            resp.sendRedirect(result);
        } else if (!password.equals(confirmedPassword)) {
            result+="?" + ERROR + "=" + UserException.PASSWORDS_MATCH_ERROR.ordinal();
            resp.sendRedirect(result);
        } else if ((firstName == null) || (firstName.equals(""))) {
            result+="?" + ERROR + "=" + UserException.EMPTY_FIRSTNAME_ERROR.ordinal();
            resp.sendRedirect(result);
        } else if ((middleName == null)||(middleName.equals(""))) {
            result+="?" + ERROR + "=" + UserException.EMPTY_MIDDLENAME_ERROR.ordinal();
            resp.sendRedirect(result);
        } else if ((lastName == null) || (lastName.equals(""))) {
            result+="?" + ERROR + "=" + UserException.EMPTY_LASTNAME_ERROR.ordinal();
            resp.sendRedirect(result);
        } else if ((birthday == null) || (birthday.equals("  -  -    ")) || (birthday.equals("")))  {
            result+="?" + ERROR + "=" + UserException.EMPTY_BIRTHDAY_ERROR.ordinal();
            resp.sendRedirect(result);
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            try {
                birthday_date = sdf.parse(birthday);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            UserCreateDTO userCreateDTO = new UserCreateDTO(
                    login,
                    password,
                    firstName,
                    middleName,
                    lastName,
                    birthday_date,
                    Role.USER
            );
            UserDTO user = userService.save(userCreateDTO);
            session.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/ui/");
        }
    }
}
