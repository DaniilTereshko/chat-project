package by.chat.services.factory;

import by.chat.dao.memory.factory.MessageDaoFactory;
import by.chat.services.MessageService;
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
