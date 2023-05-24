package by.chat.services;


import by.chat.core.dto.Role;
import by.chat.core.dto.UserCreateDTO;
import by.chat.core.dto.UserDTO;
import by.chat.core.enums.UserException;
import by.chat.core.exception.RegistrationException;
import by.chat.dao.api.IUserDao;
import by.chat.services.api.IUserService;

import java.util.*;
import java.util.regex.Pattern;

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
        validate(item);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userDao.get().size()+1);
        userDTO.setLogin(item.getLogin());
        userDTO.setFirstName(item.getFirstName());
        userDTO.setMiddleName(item.getMiddleName());
        userDTO.setLastName(item.getLastName());
        userDTO.setBirthday(item.getBirthday());
        userDTO.setPassword(item.getPassword());
        userDTO.setRegistrationDate(new Date());
        userDTO.setRole(item.getRole());
        userDao.save(userDTO);
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

    @Override
    public UserDTO delete(int id) {
        return userDao.delete(id);
    }
    @Override
    public UserDTO changeRole(UserDTO userDTO, String role, UserDTO user) {
        if(userDTO != null && (!user.getLogin().equals(userDTO.getLogin())) && (!userDTO.getRole().getRoleName().equals(role)) && (!userDTO.getRole().equals(Role.ADMIN))){
            Role roleValue = Role.valueOf(role);
            userDTO.setRole(roleValue);
        }
        return userDTO;
    }
    private void validate(UserCreateDTO userDTO){
        Map<UserException, String> invalidFields = new HashMap<>();
        Pattern patternNames = Pattern.compile("[!@#$%^&*()_+=\\[\\]{}|\\\\;:'\",.<>/?0-9\\s-]");
        Pattern patternLoginAndPassword = Pattern.compile("[+=\\s-]");

        String login = userDTO.getLogin();
        String password = userDTO.getPassword();
        String firstName = userDTO.getFirstName();
        String middleName = userDTO.getMiddleName();
        String lastName = userDTO.getLastName();

        if(patternLoginAndPassword.matcher(login).find() && login.length() <= 35){
            invalidFields.put(UserException.LOGIN_ERROR, "Логин не должен содержать пробелы и символы -+=. Максимальный размер поля 35.");
        }
        if(patternLoginAndPassword.matcher(password).find() && password.length() <= 35){
            invalidFields.put(UserException.PASSWORD_ERROR, "Пароль не должен содержать пробелы и символы -+=. Максимальный размер поля 35.");
        }
        if(patternNames.matcher(firstName).find() && firstName.length() <= 35){
            invalidFields.put(UserException.FIRSTNAME_ERROR, "Имя должно содержать только буквы. Максимальный размер поля 35.");
        }
        if(patternNames.matcher(middleName).find() && middleName.length() <= 35){
            invalidFields.put(UserException.MIDDLENAME_ERROR, "Отчество должно содержать только буквы. Максимальный размер поля 35.");
        }
        if(patternNames.matcher(lastName).find() && lastName.length() <= 35){
            invalidFields.put(UserException.LASTNAME_ERROR, "Фамилия должна содержать только буквы. Максимальный размер поля 35.");
        }
        if(!invalidFields.isEmpty()){
            throw new RegistrationException(invalidFields);
        }
    }
}
