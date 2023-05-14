package by.chat.core.dto;


import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class UserCreateDTO {
    private int id;
    private String login;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date birthday;

    private Role role;

    public UserCreateDTO() {
    }

    public UserCreateDTO(String login,
                         String password,
                         String firstName,
                         String middleName,
                         String lastName,
                         Date birthday,
                         Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.role = role;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }





    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCreateDTO that = (UserCreateDTO) o;
        return id == that.id && Objects.equals(login, that.login) && Objects.equals(password, that.password) && Objects.equals(firstName, that.firstName) && Objects.equals(middleName, that.middleName) && Objects.equals(lastName, that.lastName) && Objects.equals(birthday, that.birthday) && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, firstName, middleName, lastName, birthday, new Date(), role);
    }
}
