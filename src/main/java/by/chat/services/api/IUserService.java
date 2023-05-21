package by.chat.services.api;


import by.chat.core.dto.UserCreateDTO;
import by.chat.core.dto.UserDTO;
import by.chat.core.exception.IncorrectUserValueException;

import java.util.List;

public interface IUserService extends ICRUDService<UserDTO, UserCreateDTO>{

    UserDTO get(String login);


    @Override
    UserDTO save(UserCreateDTO item) throws IncorrectUserValueException;

    @Override
    List<UserDTO> get();


}
