package by.chat.connectors.web.listeners;

import by.chat.core.dto.UserDTO;
import by.chat.services.api.IUserStatisticsService;
import by.chat.services.factory.UserStatisticServiceFactory;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        IUserStatisticsService statisticsService = UserStatisticServiceFactory.getInstance();
        // если сессия содержала объект user и была уничтожена по каким-либо причинам учитываем этот факт
        UserDTO user = (UserDTO) se.getSession().getAttribute("user");
        if (user != null) {
            statisticsService.decCountActiveUser();
        }
    }
}
