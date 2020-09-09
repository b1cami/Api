package dgsw.b1cami.cocode.Repository;

import dgsw.b1cami.cocode.Domain.Chat;
import dgsw.b1cami.cocode.Domain.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    ArrayList<Chat> findByChatUser(ChatUser chatUser);

}
