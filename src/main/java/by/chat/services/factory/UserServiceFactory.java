package by.chat.services.factory;


import by.chat.dao.memory.factory.UserDaoFactory;
import by.chat.services.UserService;
import by.chat.services.api.IUserService;

public class  UserServiceFactory {
    private static volatile IUserService instance;
    private UserServiceFactory(){

    }
    public static IUserService getInstance(){
        if (instance ==null){
            synchronized (UserServiceFactory.class){
                if (instance == null){
                    instance = new UserService(UserDaoFactory.getInstance());
                }
            }
        }
        return instance;
    }
}
