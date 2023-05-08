package by.chat.dao.memory;


import by.chat.core.dto.UserDTO;
import by.chat.dao.api.IUserDao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMemoryDao implements IUserDao {
    private final Map<Integer, UserDTO> users = new HashMap<>();

//    String login,
//    String password,
//    String firstName,
//    String middleName,
//    String lastName,
//    Calendar birthday,
//    Calendar registrationDate,
//    String role)
    public UserMemoryDao() {
        {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Calendar birthday = Calendar.getInstance();
            Calendar regDay = Calendar.getInstance();
            try {
                birthday.setTime(sdf.parse("20-02-1990"));
                regDay.setTime(sdf.parse("20-02-2013"));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            UserDTO dto = new UserDTO(1, "123", "321", "Денис", "Александрович", "Кузько",  birthday, regDay, "Admin");
            users.put(dto.getId(), dto);
        }
        {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Calendar birthday = Calendar.getInstance();
            Calendar regDay = Calendar.getInstance();
            try {
                birthday.setTime(sdf.parse("19-02-1990"));
                regDay.setTime(sdf.parse("20-02-2014"));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            UserDTO dto = new UserDTO(2, "Max", "111", "Максим", "Александрович", "Иванов",  birthday, regDay, "Пользователь");
            users.put(dto.getId(), dto);
        }
        {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Calendar birthday = Calendar.getInstance();
            Calendar regDay = Calendar.getInstance();
            try {
                birthday.setTime(sdf.parse("19-02-1992"));
                regDay.setTime(sdf.parse("20-02-2017"));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            UserDTO dto = new UserDTO(3, "Olya", "111", "Ольга", "Александровна", "Сидор",  birthday, regDay, "Пользователь");
            users.put(dto.getId(), dto);
        }
    }

    @Override
    public List<UserDTO> get() {
        return new ArrayList<>(this.users.values());
    }

    @Override
    public UserDTO save(UserDTO item) {
        this.users.put(item.getId(), item);
        return item;
    }

    @Override
    public UserDTO get(int id) {
        return this.users.get(id);
    }
}
