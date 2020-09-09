package dgsw.b1cami.cocode.Service;

import dgsw.b1cami.cocode.Domain.Chat;
import dgsw.b1cami.cocode.json.ChatsResponse;
import dgsw.b1cami.cocode.json.Response;

public interface ChatService {

    Response chat(Chat chat, String key);

    ChatsResponse getChats(String key, Integer user2Id);

}