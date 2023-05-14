package by.chat.servlets.api;


import by.chat.core.dto.Role;
import by.chat.core.dto.UserCreateDTO;
import by.chat.core.dto.UserDTO;
import by.chat.services.api.IUserService;
import by.chat.services.factory.UserServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@WebServlet(name = "UserServlet", urlPatterns = "/api/user")

public class UserServlet_2 extends HttpServlet {
    private static final String LOGIN_PARAM_NAME = "login";
    private static final String PASSWORD_PARAM_NAME = "password";
    private static final String CONFIRMED_PASSWORD_PARAM_NAME = "confirmed_password";
    private static final String FIRST_NAME_PARAM_NAME = "firstName";
    private static final String MIDDLE_NAME_PARAM_NAME = "middleName";
    private static final String LAST_NAME_PARAM_NAME = "lastName";
    private static final String BIRTHDAY_PARAM_NAME = "birthday";


    private final IUserService userService;

    public UserServlet_2() {
        this.userService = UserServiceFactory.getInstance();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // палучаем параметры
        Calendar calendar = Calendar.getInstance();
        Date birthday_date = null;
        String login = req.getParameter(LOGIN_PARAM_NAME);
        String password = req.getParameter(PASSWORD_PARAM_NAME);
        String confirmedPassword = req.getParameter(CONFIRMED_PASSWORD_PARAM_NAME);
        String firstName = req.getParameter(FIRST_NAME_PARAM_NAME);
        String middleName = req.getParameter(MIDDLE_NAME_PARAM_NAME);
        String lastName = req.getParameter(LAST_NAME_PARAM_NAME);
        String birthday = req.getParameter(BIRTHDAY_PARAM_NAME);

        // проверяем на совпадение такого user по логину
        // проверяем на заполнение полей
        if ((login == null) || (login == "")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Вы не ввели логин");
        } else {
            UserDTO userDTO = userService.get(login);
            if (userDTO != null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Пользователь с таким логином уже существует");
            }
        }
        if((password == null) || (password == "")){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Вы не пароль");
        } else if (!password.equals(confirmedPassword)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Введённые пароли не совпадают");
        } else if ((firstName == null) || (firstName == "")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Вы не ввели имя");
        } else if ((middleName == null)||(middleName == "")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Вы не ввели отчество");
        } else if ((lastName == null) || (lastName == "")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Вы не ввели фамилию");
        } else if ((birthday == null) || (birthday == "  .  .    ") || (birthday == ""))  {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Вы не ввели дату роджения");
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

            try {
                birthday_date = sdf.parse(birthday);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        }

        // сохраняем нового пользователя
        UserCreateDTO userCreateDTO = new UserCreateDTO(
                login,
                password,
                firstName,
                middleName,
                lastName,
                birthday_date,
                new Date(),
                Role.USER
        );
        userService.save(userCreateDTO);
    }
}