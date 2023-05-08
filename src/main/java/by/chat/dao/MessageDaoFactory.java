package by.chat.dao;

import by.chat.dao.api.IMessageDao;

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
