package by.chat.services;

import by.chat.core.dto.UserCreateDTO;
import by.chat.core.dto.UserDTO;
import by.chat.dao.api.IUserDao;
import by.chat.core.exception.IncorrectUserValueException;
import by.chat.services.api.IUserService;

import java.util.Date;
import java.util.List;
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
    public UserDTO save(UserCreateDTO item) throws IncorrectUserValueException {

        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(checkOnRightName(item.getLogin(), true));
        userDTO.setFirstName(checkOnRightName(item.getFirstName(), false));
        userDTO.setMiddleName(checkOnRightName(item.getMiddleName(), false));
        userDTO.setLastName(checkOnRightName(item.getLastName(), false));
        userDTO.setId(item.hashCode());
        userDTO.setBirthday(item.getBirthday());
        userDTO.setPassword(item.getPassword());
        userDTO.setRegistrationDate(new Date());
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

     private String checkOnRightName(String item, boolean isLogin) throws IncorrectUserValueException {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

        // проверка на пустую строку, удаляем начальные и конечные пробелы
        if (item == null) {
            throw new IncorrectUserValueException("поля ЛОГИН, ФАМИЛИЯ, ИМЯ, ОТЧЕСТВО не могут быть пустыми");
        } else {
            item = item.trim();
        }
        // проверка на кол-ва слов
        String[] words = item.split("\\s+");
        if (words.length > 1) {
           throw new IncorrectUserValueException("поля ЛОГИН, ФАМИЛИЯ, ИМЯ, ОТЧЕСТВО могут содержать только 1 слово");
        }

        // проверка на длинну слова (не меньше 2 символов)
        if (item.length() < 2) {
            throw new IncorrectUserValueException("поля ЛОГИН, ФАМИЛИЯ, ИМЯ, ОТЧЕСТВО должны содержать не менее 2-ух символов");
        }

        if(isLogin == false) {
            // провверка на наличие цифр в строке
            if (pattern.matcher(item).matches() == true) {
                throw new IncorrectUserValueException("поля ФАМИЛИЯ, ИМЯ, ОТЧЕСТВО не могут содержать цифр");
            }
        }

        if(isLogin == false) {
            // проверка на наличие только букв в имени/логине
            char[] chars = item.toCharArray();
            for (char c : chars) {
                if (!Character.isLetter(c)) {
                    throw new IncorrectUserValueException("поля ФАМИЛИЯ, ИМЯ, ОТЧЕСТВО не может содержать символов");
                }
            }
        }

        if(isLogin == false){
            item = prepareName(item);
        }
        return item;
    }

    private String prepareName(String item) {
        item = item.trim();
        item = item.substring(0, 1).toUpperCase() + item.substring(1).toLowerCase();
        return item;
    }
}




