package by.chat.listeners;

import by.chat.core.dto.UserDTO;
import by.chat.services.api.IStatisticsService;
import by.chat.services.factory.StatisticsServiceFactory;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
    IStatisticsService statisticsService;

    public SessionListener(IStatisticsService statisticsService) {
        this.statisticsService = StatisticsServiceFactory.getInstance();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // если сессия содержала объект user и была уничтожена по каким-либо причинам учитываем этот факт
        UserDTO user = (UserDTO) se.getSession().getAttribute("user");
        if (user != null) {
            statisticsService.decCountActiveUser();
        }
    }
}
