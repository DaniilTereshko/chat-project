package by.chat.dao.memory;


import by.chat.core.dto.Role;
import by.chat.core.dto.UserDTO;
import by.chat.dao.api.IUserDao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
            Date birthday;
            Date regDay;
            try {
                birthday = sdf.parse("20-02-1990");
                regDay = sdf.parse("20-02-2013");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            UserDTO dto = new UserDTO(1, "123", "321", "Денис", "Александрович", "Кузько",  birthday, regDay, Role.ADMIN);
            users.put(dto.getId(), dto);
        }
        {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date birthday;
            Date regDay;
            try {
                birthday = sdf.parse("19-02-1990");
                regDay = sdf.parse("20-02-2014");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            UserDTO dto = new UserDTO(2, "Max", "111", "Максим", "Александрович", "Иванов",  birthday, regDay, Role.USER);
            users.put(dto.getId(), dto);
        }
        {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date birthday;
            Date regDay;
            try {
                birthday = sdf.parse("19-02-1992");
                regDay = sdf.parse("20-02-2017");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            UserDTO dto = new UserDTO(3, "Olya", "111", "Ольга", "Александровна", "Сидор",  birthday, regDay, Role.USER);
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

    @Override
    public UserDTO get(String login) {
        return users.values().stream().filter(v -> v.getLogin().equals(login)).findFirst().orElse(null);
    }
}
