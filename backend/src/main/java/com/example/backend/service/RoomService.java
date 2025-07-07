package com.example.backend.service;

import com.example.backend.entities.Message;
import com.example.backend.entities.Room;

import java.util.List;

public interface RoomService {
    Room createRoom(String roomId);
    Room getRoomByRoomId(String roomId);
    List<Message> getMessages(String roomId, int page, int size);
}
