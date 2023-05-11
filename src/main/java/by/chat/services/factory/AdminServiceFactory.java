package by.chat.services.factory;

import by.chat.dao.memory.factory.MessageDaoFactory;
import by.chat.services.AdminService;
import by.chat.services.api.IAdminService;

public class AdminServiceFactory {
    private static volatile IAdminService instance;

    private AdminServiceFactory() {
    }
    public static IAdminService getInstance(){
        if (instance == null) {
            synchronized (MessageDaoFactory.class) {
                if (instance == null) {
                    instance = new AdminService(UserServiceFactory.getInstance()) {
                    };
                }
            }
        }
        return instance;
    }
}
