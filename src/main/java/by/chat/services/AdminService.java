package by.chat.services;

import by.chat.core.dto.UserDTO;
import by.chat.services.api.IAdminService;
import by.chat.services.api.IUserService;
import by.chat.services.factory.UserServiceFactory;

public class AdminService implements IAdminService {
    private final IUserService userService;

    public AdminService(IUserService userService) {
        this.userService = UserServiceFactory.getInstance();
    }

    @Override
    public UserDTO changeRole(UserDTO userDTO, String role) {
        return null;
    }
}
