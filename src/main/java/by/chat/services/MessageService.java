package by.chat.services;

import by.chat.core.dto.MessageDTO;
import by.chat.dao.api.IMessageDao;
import by.chat.services.api.IMessageService;

import java.util.ArrayList;
import java.util.List;

public class MessageService implements IMessageService {
    private final IMessageDao messageDao;

    public MessageService(IMessageDao messageDao) {
        this.messageDao=messageDao;
    }

    @Override
    public List<MessageDTO> get() {
        return messageDao.get();
    }

    @Override
    public List<MessageDTO> get(int id) {
        List<MessageDTO> result = new ArrayList<>();
        for (MessageDTO massage:messageDao.get()){
            if (massage.getToUserId() == id){
                result.add(massage);
            }
        }
        return result;
    }

    @Override
    public MessageDTO save(MessageDTO message) {
        return messageDao.save(message);
    }
}
