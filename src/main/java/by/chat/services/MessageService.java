package by.chat.services;

import by.chat.core.dto.MessageCreateDTO;
import by.chat.core.dto.MessageDTO;
import by.chat.dao.api.IMessageDao;
import by.chat.services.api.IMessageService;
import by.chat.services.api.IUserService;
import by.chat.services.factory.UserServiceFactory;

import java.util.ArrayList;
import java.util.List;

public class MessageService implements IMessageService {
    private final IMessageDao messageDao;
    private final IUserService userService;

    public MessageService(IMessageDao messageDao) {
        this.messageDao=messageDao;
        this.userService= UserServiceFactory.getInstance();

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
    public MessageDTO save(MessageCreateDTO message) {
        String info = userService.get(message.getFromUserId()).getLastName()+" "+
                userService.get(message.getFromUserId()).getFirstName()+" "+
                userService.get(message.getFromUserId()).getMiddleName();
        MessageDTO dto = new MessageDTO(messageDao.get().size()+1,info,message);
        return messageDao.save(dto);
    }

    @Override
    public Integer delet(int id) {
        messageDao.delet(id);
        return id;
    }
}
