package com.cs2043.messenger.app.chat_room.models;

import com.cs2043.messenger.app.message.models.Message;
import com.cs2043.messenger.app.user.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ChatRoom {
    @Id
    @SequenceGenerator(name = "chat_room_sequence", sequenceName = "chat_room_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_room_sequence")
    private Long id;

    @CreationTimestamp
    private LocalDateTime creationTimeStamp;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private List<Message> messages;

    @ManyToMany
    @JoinTable(
            name = "chat_room_users",
            joinColumns = @JoinColumn(name = "chat_room_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> participants;
}
