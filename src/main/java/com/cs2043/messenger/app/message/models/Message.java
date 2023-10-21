package com.cs2043.messenger.app.message.models;

import com.cs2043.messenger.app.chat_room.models.ChatRoom;
import com.cs2043.messenger.app.user.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Message {
    @Id
    @SequenceGenerator(name = "message_sequence", sequenceName = "message_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_sequence")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "chat_room_id", referencedColumnName = "id") // Specify foreign key column name and referenced column name
    ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id") // Specify foreign key column name and referenced column name
    User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "id") // Specify foreign key column name and referenced column name
    User receiver;

    String text;

    @CreationTimestamp
    LocalDateTime creationTimeStamp;
}
