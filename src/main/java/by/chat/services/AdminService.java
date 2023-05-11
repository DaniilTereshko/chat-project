package by.chat.services;

import by.chat.core.dto.Role;
import by.chat.core.dto.UserDTO;
import by.chat.services.api.IAdminService;
import by.chat.services.api.IUserService;
import by.chat.services.factory.UserServiceFactory;

public class AdminService implements IAdminService {
    private final IUserService userService;

    public AdminService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDTO changeRole(UserDTO userDTO, String role, UserDTO user) {
        if(userDTO != null && (!user.getLogin().equals(userDTO.getLogin())) && (!userDTO.getRole().getRoleName().equals(role)) && (!userDTO.getRole().equals(Role.ADMIN))){
            Role roleValue = Role.valueOf(role);
            userDTO.setRole(roleValue);
        }
        return userDTO;
    }
}
