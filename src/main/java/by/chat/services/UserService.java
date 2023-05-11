package by.chat.services;


import by.chat.core.dto.Role;
import by.chat.core.dto.UserCreateDTO;
import by.chat.core.dto.UserDTO;
import by.chat.dao.api.IUserDao;
import by.chat.services.api.IUserService;

import java.util.Calendar;
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
        UserDTO userDTO = new UserDTO();
 //     userDTO.setId(item.hashCode());
        userDTO.setId(userDao.get().size() + 1);
        userDTO.setLogin(item.getLogin());
        userDTO.setFirstName(item.getFirstName());
        userDTO.setMiddleName(item.getMiddleName());
        userDTO.setLastName(item.getLastName());
        userDTO.setBirthday(item.getBirthday());
        userDTO.setPassword(item.getPassword());
        userDTO.setRegistrationDate(item.getRegistrationDate());
        userDTO.setRole(item.getRole());
        return userDTO;
    }

    @Override
    public UserDTO get(int id) {
        return userDao.get(id);
    }

    @Override
    public UserDTO get(String login) {
        return userDao.get(login);
    }
}
