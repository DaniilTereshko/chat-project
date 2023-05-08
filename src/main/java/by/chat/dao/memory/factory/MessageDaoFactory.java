package by.chat.dao.memory.factory;

import by.chat.dao.api.IMessageDao;
import by.chat.dao.memory.MessageMemoryDao;

public class MessageDaoFactory {
    private static IMessageDao instance;

    private MessageDaoFactory() {
    }
    public static IMessageDao getInstance(){
        if (instance == null) {
            synchronized (MessageDaoFactory.class) {
                if (instance == null) {
                    instance = new MessageMemoryDao() {
                    };
                }
            }
        }
        return instance;
    }
}
