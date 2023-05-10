package by.chat.core.dto;


import java.util.Date;

public class MessageDTO {
    private Integer fromUserId;
    private Integer toUserId;
    private String message;
    private Date date;

    private String info;


    public MessageDTO(String message, Integer fromId, Integer toId, String info) {
        this.message = message;
        this.fromUserId = fromId;
        this.toUserId = toId;
        this.info = info;
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

    public String getInfo() {
        return info;
    }
    public Date getDate() {
        return date;
    }
}
