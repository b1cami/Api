package dgsw.b1cami.cocode.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long chatId;

    @Column(nullable = false)
    ChatUser chatUser;

    @Column(nullable = false)
    String chatText;

    @Column(nullable = false)
    @CreationTimestamp
    LocalDateTime postUploaded;

    public Chat(Long id, ChatUser sender, String text) {
        this.setId(id);
        this.setUser(sender);
        this.setText(text);
    }

    public Long getId() {
        return chatId;
    }

    public void setId(Long id) {
        this.chatId = id;
    }

    public ChatUser getUser() {
        return chatUser;
    }

    public void setUser(ChatUser user) {
        this.chatUser = user;
    }

    public String getText() {
        return chatText;
    }

    public void setText(String text) {
        this.chatText = text;
    }

    public LocalDateTime getUploaded() {
        return postUploaded;
    }

    public void setUploaded(LocalDateTime uploaded) {
        this.postUploaded = uploaded;
    }

}
