package by.chat.core.dto;


import java.util.Date;

public class MessageDTO {
    private Integer id;
    private Integer fromUserId;
    private Integer toUserId;
    private String message;
    private Date date;

    private String info;


    public MessageDTO(int id, String info,MessageCreateDTO messagec) {
        this.id =id;
        this.message = messagec.getMessage();
        this.fromUserId = messagec.getFromUserId();
        this.toUserId = messagec.getToUserId();
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

    public Integer getId() {
        return id;
    }

    public String getInfo() {
        return info;
    }
    public Date getDate() {
        return date;
    }
}
