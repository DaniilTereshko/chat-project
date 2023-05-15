package by.chat.services.factory;

import by.chat.dao.memory.factory.MessageDaoFactory;
import by.chat.services.MessageService;
import by.chat.services.StatisticsService;
import by.chat.services.api.IMessageService;
import by.chat.services.api.IStatisticsService;

public class StatisticsServiceFactory {
    private static volatile IStatisticsService instance;

    private StatisticsServiceFactory() {
    }

    public static IStatisticsService getInstance() {
        if (instance == null) {
            synchronized (StatisticsServiceFactory.class) {
                if (instance == null) {
                    instance = new StatisticsService() {
                    };
                }
            }
        }
        return instance;
    }
}
