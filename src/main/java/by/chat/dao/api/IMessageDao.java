package by.chat.dao.api;

import by.chat.core.dto.MessageCreateDTO;
import by.chat.core.dto.MessageDTO;

import java.util.List;

public interface IMessageDao {
    List<MessageDTO> get();
    MessageDTO save(MessageDTO message);
    Integer delet(int id);




}
