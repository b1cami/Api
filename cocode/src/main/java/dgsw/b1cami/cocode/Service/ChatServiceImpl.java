package dgsw.b1cami.cocode.Service;

import dgsw.b1cami.cocode.Domain.*;
import dgsw.b1cami.cocode.Exception.UserException;
import dgsw.b1cami.cocode.Repository.ChatRepository;
import dgsw.b1cami.cocode.Repository.TokenRepository;
import dgsw.b1cami.cocode.Repository.UserRepository;
import dgsw.b1cami.cocode.json.ChatsResponse;
import dgsw.b1cami.cocode.json.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    ChatRepository chatRepository;

    @Override
    public Response chat(Chat chat, String key) {
        try {
            Token token = tokenRepository.findByTokenKey(key).orElseThrow(
                    () -> new UserException(400, "Undefined Token Key")
            );

            User sender = userRepository.findByUserId(token.getOwnerId()).orElseThrow(
                    () -> new UserException(400, "Unreachable Code - Just For Deleting Yellow Line")
            );

            ChatUser chatUser = chat.getUser();
            if(chatUser == null)
                throw new UserException(400, "Undefined ChatUser");

            Integer receiverId = chatUser.getReceiverId();
            String text = chat.getText();

            if(receiverId == null)
                throw new UserException(400, "Requires ReceiverId");
            if(text == null)
                throw new UserException(400, "Requires Text");

            userRepository.findByUserId(receiverId).orElseThrow(
                    () -> new UserException(400, "Undefined ReceiverId")
            );

            chat.getUser().setSenderId(sender.getId());
            chatRepository.save(chat);
            return new Response(200, "Success Chat");
        } catch (UserException e) {
            return new Response(e.getStatus(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(500, e.getMessage());
        }
    }

    @Override
    public ChatsResponse getChats(String key, Integer user2Id) {
        try {
            Token token = tokenRepository.findByTokenKey(key).orElseThrow(
                    () -> new UserException(400, "Undefined Token Key")
            );
            Integer user1Id = userRepository.findByUserId(token.getOwnerId()).orElseThrow(
                    () -> new UserException(400, "Unreachable Code - Just For Deleting Yellow Line")
            ).getId();

            userRepository.findByUserId(user2Id).orElseThrow(
                    () -> new UserException(400, "Undefined User2Id")
            );

            ArrayList<ChatOutput> chats = new ArrayList<>();
            for(Chat chat : chatRepository.findByChatUser(new ChatUser(user1Id, user2Id)))
                chats.add(new ChatOutput(chat));
            for(Chat chat : chatRepository.findByChatUser(new ChatUser(user2Id, user1Id)))
                chats.add(new ChatOutput(chat));

            return new ChatsResponse(200, "Success GetChats", chats);
        } catch (UserException e) {
            return new ChatsResponse(e.getStatus(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new ChatsResponse(500, e.getMessage());
        }
    }

}
