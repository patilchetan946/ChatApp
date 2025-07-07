package com.example.backend.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.backend.entities.Room;

public interface RoomDao extends MongoRepository<Room, String> {
	
	Room findByRoomId(String roomId);

}
