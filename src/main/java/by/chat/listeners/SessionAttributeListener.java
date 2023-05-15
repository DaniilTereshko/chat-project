package by.chat.listeners;

import by.chat.core.dto.UserDTO;
import by.chat.services.api.IStatisticsService;
import by.chat.services.factory.StatisticsServiceFactory;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionListener;

// Листенер который слушает изменение атрибутов
@WebListener
public class SessionAttributeListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        IStatisticsService statisticsService = StatisticsServiceFactory.getInstance();
        //event.getName() + ": " + event.getValue()
        if (event.getName() == "user") {
            UserDTO user = (UserDTO) event.getSession().getAttribute("user");
            if (user != null) {
                statisticsService.addCountActiveUser();
            }
        }

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        IStatisticsService statisticsService = StatisticsServiceFactory.getInstance();
        if (event.getName() == "user") {
            UserDTO user = (UserDTO) event.getSession().getAttribute("user");
            if (user != null) {
                statisticsService.decCountActiveUser();
            }
        }
    }
}
