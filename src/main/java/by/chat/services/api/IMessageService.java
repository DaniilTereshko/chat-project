package by.chat.services.api;

import by.chat.core.dto.MessageDTO;

import java.util.List;

public interface IMessageService {
    List<MessageDTO> get();
    List<MessageDTO> get(int id);
    MessageDTO save(MessageDTO message);
}
