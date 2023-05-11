package by.chat.services.api;

import by.chat.core.dto.UserDTO;

public interface IAdminService {
    UserDTO changeRole(UserDTO userDTO, String role);
}
