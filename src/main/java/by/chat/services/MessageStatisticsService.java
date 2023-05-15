package by.chat.services;

import by.chat.services.api.IMessageService;
import by.chat.services.api.IMessageStatisticsService;

public class MessageStatisticsService implements IMessageStatisticsService {
    private final IMessageService messageService;

    public MessageStatisticsService(IMessageService messageService) {
        this.messageService = messageService;
    }
    @Override
    public Integer getCountMessages() {
        return messageService.get().size();
    }
}
