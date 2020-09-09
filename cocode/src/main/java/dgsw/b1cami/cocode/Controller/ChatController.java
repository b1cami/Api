package dgsw.b1cami.cocode.Controller;

import dgsw.b1cami.cocode.Domain.Chat;
import dgsw.b1cami.cocode.Service.ChatService;
import dgsw.b1cami.cocode.json.ChatsResponse;
import dgsw.b1cami.cocode.json.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    ChatService chatService;

    @PostMapping("/chat")
    public ResponseEntity<Response> chat(@RequestBody Chat chat, @RequestHeader(name = "Token") String key) {
        if(chat.getUser() != null)
            System.out.println("chat chat - " + key + " -> " + chat.getUser().getReceiverId());
        else
            System.out.println("chat chat - " + key + " -> null");

        return new ResponseEntity<>(chatService.chat(chat, key), HttpStatus.OK);
    }

    @GetMapping("/getChats/{user2Id}")
    public ResponseEntity<ChatsResponse> getChats(@RequestHeader(name = "Token") String key, @PathVariable Integer user2Id) {
        System.out.println("chat getChats - " + key + ", " + user2Id);
        return new ResponseEntity<>(chatService.getChats(key, user2Id), HttpStatus.OK);
    }

}
