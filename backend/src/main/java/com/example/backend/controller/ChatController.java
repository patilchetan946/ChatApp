package com.example.backend.controller;

import java.time.LocalDateTime;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.backend.dao.RoomDao;
import com.example.backend.entities.Message;
import com.example.backend.entities.Room;
import com.example.backend.payload.MessageRequest;


@Controller
public class ChatController {

	private RoomDao roomDao;

	public ChatController(RoomDao roomDao) {
		super();
		this.roomDao = roomDao;
	}
	//for sending and receiving messages
	@MessageMapping("/sendmessage/{roomId}")
	@SendTo("/topic/room/{roomId}")
	public Message sendMessages(
			@DestinationVariable String roomId,
			@RequestBody MessageRequest request 
			) throws Exception {
		Room room=roomDao.findByRoomId(request.getRoomId());
		
		Message message = new Message();
		message.setContent(request.getContent());
		message.setSender(request.getSender());
		message.setTimestamp(LocalDateTime.now());
		
		if(room != null) {
			room.getMessages().add(message);
			roomDao.save(room);
		}else {
			throw new Exception("room not found !");
		}
		return message;
	}
}
