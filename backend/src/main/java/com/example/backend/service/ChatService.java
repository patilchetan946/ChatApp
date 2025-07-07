package com.example.backend.service;

import com.example.backend.entities.Message;
import com.example.backend.payload.MessageRequest;

public interface ChatService {
    Message handleIncomingMessage(String roomId, MessageRequest request) throws Exception;
}
