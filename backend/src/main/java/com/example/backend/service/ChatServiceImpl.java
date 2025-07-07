package com.example.backend.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.backend.dao.RoomDao;
import com.example.backend.entities.Message;
import com.example.backend.entities.Room;
import com.example.backend.payload.MessageRequest;

@Service
public class ChatServiceImpl implements ChatService {

    private final RoomDao roomDao;

    public ChatServiceImpl(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    public Message handleIncomingMessage(String roomId, MessageRequest request) throws Exception {
        Room room = roomDao.findByRoomId(request.getRoomId());

        if (room == null) {
            throw new Exception("Room not found!");
        }

        Message message = new Message();
        message.setSender(request.getSender());
        message.setContent(request.getContent());
        message.setTimestamp(LocalDateTime.now());

        room.getMessages().add(message);
        roomDao.save(room);

        return message;
    }
}
