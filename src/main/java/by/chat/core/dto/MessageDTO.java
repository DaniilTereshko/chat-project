package by.chat.core.dto;


import java.util.Date;

public class MessageDTO {
    private Integer fromUserId;
    private Integer toUserId;
    private String message;
    private Date date;


    public MessageDTO(String message, Integer fromId, Integer toId) {
        this.message = message;
        this.fromUserId = fromId;
        this.toUserId = toId;
        this.date = new Date();
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

    public Date getDate() {
        return date;
    }
}
