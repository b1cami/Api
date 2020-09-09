package dgsw.b1cami.cocode.Domain;

import java.time.LocalDateTime;

public class ChatOutput {

    Integer senderId;

    Integer receiverId;

    String text;

    LocalDateTime uploaded;

    public ChatOutput(Chat chat) {
        this.senderId = chat.getUser().getSenderId();
        this.receiverId = chat.getUser().getReceiverId();
        this.text = chat.getText();
        this.uploaded = chat.getUploaded();
    }

    public Integer getSenderId() {
        return senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getUploaded() {
        return uploaded;
    }

}
