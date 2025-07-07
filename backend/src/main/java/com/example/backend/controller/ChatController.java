package com.example.backend.controller;

import com.example.backend.entities.Message;
import com.example.backend.payload.MessageRequest;
import com.example.backend.service.ChatService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@CrossOrigin("http://localhost:3000")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/sendmessage/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public Message sendMessages(
            @DestinationVariable String roomId,
            @RequestBody MessageRequest request
    ) throws Exception {
        return chatService.handleIncomingMessage(roomId, request);
    }
}
