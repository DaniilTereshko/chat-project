package by.chat.services.api;

import by.chat.core.dto.MessageCreateDTO;
import by.chat.core.dto.MessageDTO;

import java.util.List;

public interface IMessageService extends ICRUDService<MessageDTO,MessageCreateDTO> {

    Integer delet(int id);
    List<MessageDTO> usersMessages(int id);
}
