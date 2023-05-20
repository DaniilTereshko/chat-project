package by.chat.connectors.web.listeners;

import by.chat.core.dto.UserDTO;
import by.chat.services.api.IUserStatisticsService;
import by.chat.services.factory.UserStatisticServiceFactory;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

// Листенер который слушает изменение атрибутов
@WebListener
public class SessionAttributeListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        IUserStatisticsService statisticsService = UserStatisticServiceFactory.getInstance();
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
        IUserStatisticsService statisticsService = UserStatisticServiceFactory.getInstance();
        if (event.getName() == "user") {
            UserDTO user = (UserDTO) event.getSession().getAttribute("user");
            if (user == null) {
                statisticsService.decCountActiveUser();
            }
        }
    }
}
