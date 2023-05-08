package by.chat.services;

import by.chat.dao.MessageDaoFactory;
import by.chat.dao.MessageMemoryDao;
import by.chat.services.api.IMessageService;

public class MessageServiceFactory {
    private static volatile IMessageService instance;

    private MessageServiceFactory() {
    }
    public static IMessageService getInstance(){
        if (instance == null) {
            synchronized (MessageDaoFactory.class) {
                if (instance == null) {
                    instance = new MessageService(MessageDaoFactory.getInstance()) {
                    };
                }
            }
        }
        return instance;
    }
}
