package by.chat.services;


import by.chat.core.dto.UserCreateDTO;
import by.chat.core.dto.UserDTO;
import by.chat.dao.api.IUserDao;
import by.chat.services.api.IUserService;

import java.util.List;

public class UserService implements IUserService {
    private final IUserDao userDao;

    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public List<UserDTO> get() {
        return userDao.get();
    }

    @Override
    public UserDTO save(UserCreateDTO item) {
     return null;
    }

    @Override
    public UserDTO get(int id) {
        return userDao.get(id);
    }
}
