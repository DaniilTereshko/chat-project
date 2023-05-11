package by.chat.core.dto;


import java.util.Date;

public class MessageCreateDTO {
    private Integer fromUserId;
    private Integer toUserId;
    private String message;



    public MessageCreateDTO(String message, Integer fromId, Integer toId) {
        this.message = message;
        this.fromUserId = fromId;
        this.toUserId = toId;
    }

    public String getMessage() {
        return message;
    }

    public Integer getFromUserId() {
        return fromUserId;
    }

    public Integer getToUserId() {
        return toUserId;
    }
}
