package dgsw.b1cami.cocode.json;

import dgsw.b1cami.cocode.Domain.ChatOutput;

public class ChatResponse extends Response {

    ChatOutput chatOutput;

    public ChatResponse(int status, String message) {
        super(status, message);
    }

    public ChatResponse(int status, String message, ChatOutput chatOutput) {
        super(status, message);
        this.chatOutput = chatOutput;
    }

    public ChatOutput getChatOutput() {
        return chatOutput;
    }

}
