package com.example.backend.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.backend.dao.RoomDao;
import com.example.backend.entities.Message;
import com.example.backend.entities.Room;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomDao roomDao;

    public RoomServiceImpl(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    public Room createRoom(String roomId) {
        if (roomDao.findByRoomId(roomId) != null) {
            return null; // Room already exists
        }
        Room room = new Room();
        room.setRoomId(roomId);
        return roomDao.save(room);
    }

    @Override
    public Room getRoomByRoomId(String roomId) {
        return roomDao.findByRoomId(roomId);
    }

    @Override
    public List<Message> getMessages(String roomId, int page, int size) {
        Room room = roomDao.findByRoomId(roomId);
        if (room == null || room.getMessages() == null) {
            return Collections.emptyList();
        }

        List<Message> messages = room.getMessages();
        int start = Math.max(0, messages.size() - (page + 1) * size);
        int end = Math.min(messages.size(), start + size);
        return messages.subList(start, end);
    }
}
