package by.chat.servlets;


import by.chat.core.dto.Role;
import by.chat.core.dto.UserCreateDTO;
import by.chat.core.dto.UserDTO;
import by.chat.services.api.IUserService;
import by.chat.services.factory.UserServiceFactory;
import jakarta.servlet.RequestDispatcher;
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

public class UserServlet extends HttpServlet {
    private static final String LOGIN_PARAM_NAME = "login";
    private static final String PASSWORD_PARAM_NAME = "password";
    private static final String CONFIRMED_PASSWORD_PARAM_NAME = "confirmed_password";
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

        // перенаправляе  пользователя на форму регистрации
        req.getRequestDispatcher("/registration.jspx").forward(req, resp);


      /*  req.setCharacterEncoding("UTF-8");
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

       */
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // палучаем параметры

        Calendar calendar = Calendar.getInstance();
        String login = req.getParameter(LOGIN_PARAM_NAME);
        String password = req.getParameter(PASSWORD_PARAM_NAME);
        String confirmedPassword = req.getParameter(CONFIRMED_PASSWORD_PARAM_NAME);
        String firstName = req.getParameter(FIRST_NAME_PARAM_NAME);
        String middleName = req.getParameter(MIDDLE_NAME_PARAM_NAME);
        String lastName = req.getParameter(LAST_NAME_PARAM_NAME);
        String birthday = req.getParameter(BIRTHDAY_PARAM_NAME);

        // проверяем на совпадение такого user по логину
        // проверяем на заполнение полей
        if (login == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Вы не ввели логин");
        } else {
            UserDTO userDTO = userService.get(login);
            if (userDTO != null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Пользователь с таким логином уже существует");
            }
        }
        if (!password.equals(confirmedPassword)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Введённые пароли не совпадают");
        } else if (firstName == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Вы не ввели имя");
        } else if (middleName == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Вы не ввели отчество");
        } else if (lastName == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Вы не ввели фамилию");
        } else if (birthday == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Вы не ввели дату роджения");
        } else {
            try {
                calendar.setTime(new Date(String.valueOf(new SimpleDateFormat("dd-MM-yyyy").parse(birthday))));
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
                calendar,
                Calendar.getInstance(),
                Role.USER
        );
        userService.save(userCreateDTO);



        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/chat-project-1.0.0/home");
        dispatcher.forward(req,resp);

    }
}
