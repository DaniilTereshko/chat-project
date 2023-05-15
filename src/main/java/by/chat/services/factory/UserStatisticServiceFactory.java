package by.chat.services.factory;

import by.chat.services.MessageStatisticsService;
import by.chat.services.UserStatisticService;
import by.chat.services.api.IMessageStatisticsService;
import by.chat.services.api.IUserStatisticsService;

public class UserStatisticServiceFactory {
    private static volatile IUserStatisticsService instance;

    private UserStatisticServiceFactory() {
    }
    public static IUserStatisticsService getInstance(){
        if (instance == null) {
            synchronized (UserStatisticServiceFactory.class) {
                if (instance == null) {
                    instance = new UserStatisticService(UserServiceFactory.getInstance());
                }
            }
        }
        return instance;
    }
}
