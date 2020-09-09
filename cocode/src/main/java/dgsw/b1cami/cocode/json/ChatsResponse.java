package dgsw.b1cami.cocode.json;

import dgsw.b1cami.cocode.Domain.ChatOutput;

import java.util.ArrayList;

public class ChatsResponse extends Response {

    ArrayList<ChatOutput> chatOutputs;

    public ChatsResponse(int status, String message) {
        super(status, message);
    }

    public ChatsResponse(int status, String message, ArrayList<ChatOutput> chatOutputs) {
        super(status, message);
        this.chatOutputs = chatOutputs;
    }

    public ArrayList<ChatOutput> getChatOutputs() {
        return chatOutputs;
    }
}
