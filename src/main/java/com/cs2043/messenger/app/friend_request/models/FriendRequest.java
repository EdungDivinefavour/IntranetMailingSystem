package com.cs2043.messenger.app.friend_request.models;

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
public class FriendRequest {
    @Id
    @SequenceGenerator(name = "friend_request_sequence", sequenceName = "friend_request_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "friend_request_sequence")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id") // Specify foreign key column name and referenced column name
    private User sender;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "id") // Specify foreign key column name and referenced column name
    private User receiver;

    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime creationTimeStamp;
}
