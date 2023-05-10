package by.chat.dao.memory;

import by.chat.core.dto.MessageDTO;
import by.chat.dao.api.IMessageDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageMemoryDao implements IMessageDao {
    private List<MessageDTO> dao;

    public MessageMemoryDao() {
        this.dao = new ArrayList<>();
        dao.add(new MessageDTO("Hello\rWorld<br/>Im OK<br/>Denis",1,1));
        dao.add(new MessageDTO("Hello\rWorld\nIm OK\nDenis",1,1));
    }

    @Override
    public List<MessageDTO> get() {
        return dao;
    }

    @Override
    public synchronized MessageDTO save(MessageDTO message) {
        dao.add(message);
        return message;
    }
}
