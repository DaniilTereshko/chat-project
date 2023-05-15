package by.chat.servlets.api.admin;


import by.chat.core.dto.Role;
import by.chat.core.dto.UserDTO;
import by.chat.services.api.IAdminService;
import by.chat.services.api.IMessageStatisticsService;
import by.chat.services.api.IUserService;
import by.chat.services.api.IUserStatisticsService;
import by.chat.services.factory.AdminServiceFactory;
import by.chat.services.factory.MessageStatisticsServiceFactory;
import by.chat.services.factory.UserServiceFactory;
import by.chat.services.factory.UserStatisticServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="StatisticServlet",urlPatterns = "/api/admin/statistics")

public class StatisticServlet extends HttpServlet {
    private final IUserService userService;
    private final IAdminService adminService;
    private final IMessageStatisticsService messageStatistics;
    private final IUserStatisticsService userStatisticsService;

    public StatisticServlet() {
        this.userService = UserServiceFactory.getInstance();
        this.adminService = AdminServiceFactory.getInstance();
        this.messageStatistics = MessageStatisticsServiceFactory.getInstance();
        this.userStatisticsService = UserStatisticServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer =resp.getWriter();

        writer.write("Количество активных пользователей: " + userStatisticsService.getCountActiveUsers() + "<br>");
        writer.write("Количество пользователей: " + userStatisticsService.getCountUsers() + "<br>");
        writer.write("Количество сообщений: " + messageStatistics.getCountMessages());
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
    }
}
