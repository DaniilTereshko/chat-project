package by.chat.controllers.web.servlets.ui.admin;

import by.chat.services.api.IMessageStatisticsService;
import by.chat.services.api.IUserStatisticsService;
import by.chat.services.factory.MessageStatisticsServiceFactory;
import by.chat.services.factory.UserStatisticServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "UIStatisticsServlet", urlPatterns = "/ui/admin/statistics")
public class UIStatisticsServlet extends HttpServlet {
    private static final String PARAM_NAME_COUNT_ACTIVE_USERS = "countActiveUsers";
    private static final String PARAM_NAME_COUNT_USERS = "countUsers";
    private static final String PARAM_NAME_COUNT_MESSAGES = "countMessages";
    private final IMessageStatisticsService messageStatistics;
    private final IUserStatisticsService userStatisticsService;

    public UIStatisticsServlet() {
        this.messageStatistics = MessageStatisticsServiceFactory.getInstance();
        this.userStatisticsService = UserStatisticServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(PARAM_NAME_COUNT_ACTIVE_USERS, userStatisticsService.getCountActiveUsers());
        req.setAttribute(PARAM_NAME_COUNT_USERS, userStatisticsService.getCountUsers());
        req.setAttribute(PARAM_NAME_COUNT_MESSAGES, messageStatistics.getCountMessages());
        req.getRequestDispatcher("/ui/admin/statistics.jsp").forward(req, resp);
    }
}
