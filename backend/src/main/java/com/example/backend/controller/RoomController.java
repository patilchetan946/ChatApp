package com.example.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entities.Message;
import com.example.backend.entities.Room;
import com.example.backend.service.RoomService;


@RestController
@RequestMapping("api/v1/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    //create room
    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody String roomId) {
        Room createdRoom = roomService.createRoom(roomId);
        if (createdRoom == null) {
            return ResponseEntity.badRequest().body("Room already exists!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoom);
    }

    //get room
    @GetMapping("/{roomId}")
    public ResponseEntity<?> join(@PathVariable String roomId) {
        Room room = roomService.getRoomByRoomId(roomId);
        if (room == null) {
            return ResponseEntity.badRequest().body("Room not found!");
        }
        return ResponseEntity.ok(room);
    }

    //get room messages
    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<Message>> getMessages(
            @PathVariable String roomId,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "20", required = false) int size) {

        List<Message> messages = roomService.getMessages(roomId, page, size);
        return ResponseEntity.ok(messages);
    }
}