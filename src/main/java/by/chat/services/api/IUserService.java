package by.chat.services.api;


import by.chat.core.dto.UserCreateDTO;
import by.chat.core.dto.UserDTO;

public interface IUserService extends ICRUDService<UserDTO, UserCreateDTO>{
    UserDTO get(String login);
    UserDTO delete(int id);

}
