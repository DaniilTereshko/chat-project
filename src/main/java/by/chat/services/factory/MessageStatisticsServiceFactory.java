package by.chat.services.factory;

import by.chat.services.MessageStatisticsService;
import by.chat.services.api.IMessageStatisticsService;

public class MessageStatisticsServiceFactory {
    private static volatile IMessageStatisticsService instance;

    private MessageStatisticsServiceFactory() {
    }

    public static IMessageStatisticsService getInstance() {
        if (instance == null) {
            synchronized (MessageStatisticsServiceFactory.class) {
                if (instance == null) {
                    instance = new MessageStatisticsService(MessageServiceFactory.getInstance());
                }
            }
        }
        return instance;
    }
}
