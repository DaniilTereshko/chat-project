package by.chat.dao.memory;

import by.chat.core.dto.MessageDTO;
import by.chat.dao.api.IMessageDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageMemoryDao implements IMessageDao {
    private Map<Integer,MessageDTO> dao;

    public MessageMemoryDao() {
        this.dao = new HashMap<>();
    }

    @Override
    public List<MessageDTO> get() {
        return new ArrayList<>(dao.values());
    }

    @Override
    public synchronized MessageDTO save(MessageDTO message) {
        dao.put(message.getId(),message);
        return message;
    }

    @Override
    public MessageDTO get(int id) {
        return dao.get(id);
    }

    @Override
    public synchronized Integer delete(int id) {
        dao.remove(id);
        return id;
    }
}
