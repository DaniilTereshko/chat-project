package by.chat.dao.memory.factory;


import by.chat.dao.api.IUserDao;
import by.chat.dao.memory.UserMemoryDao;

public class UserDaoFactory {
    private static volatile IUserDao instance;

    private UserDaoFactory() {
    }

    public static IUserDao getInstance() {
        if (instance == null) {
            synchronized (UserDaoFactory.class) {
                if (instance == null) {
                    instance = new UserMemoryDao();
                }
            }
        }
        return instance;
    }
}
