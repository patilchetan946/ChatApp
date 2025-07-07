package com.example.backend.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "rooms")
public class Room {

    @Id
    private String id;

    private String roomId;

    private List<Message> messages = new ArrayList<>();
}
