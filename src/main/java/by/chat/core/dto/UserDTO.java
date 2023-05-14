package by.chat.core.dto;



import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class UserDTO {
    private int id;
    private String login;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date birthday;
    private Date registrationDate;
    private Role role;

    public UserDTO() {
    }

    public UserDTO(int id,
                   String login,
                   String password,
                   String firstName,
                   String middleName,
                   String lastName,
                   Date birthday,
                   Date registrationDate,
                   Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.registrationDate = registrationDate;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
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
        UserDTO userDTO = (UserDTO) o;
        return id == userDTO.id && Objects.equals(login, userDTO.login) && Objects.equals(password, userDTO.password) && Objects.equals(firstName, userDTO.firstName) && Objects.equals(middleName, userDTO.middleName) && Objects.equals(lastName, userDTO.lastName) && Objects.equals(birthday, userDTO.birthday) && Objects.equals(registrationDate, userDTO.registrationDate) && role == userDTO.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, firstName, middleName, lastName, birthday, registrationDate, role);
    }
}
