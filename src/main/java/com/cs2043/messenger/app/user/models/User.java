package com.cs2043.messenger.app.user.models;

import com.cs2043.messenger.app.branch.models.Branch;
import com.cs2043.messenger.app.chat_room.models.ChatRoom;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "id") // Specify foreign key column name and referenced column name
    private Branch branch;

    private String name;
    private String email;

    @JsonIgnore
    private String password;

    private String phoneNumber;
    private String address;
    private String jobTitle;
    private String profilePhoto;
    private LocalDateTime dateOfBirth;

    @CreationTimestamp
    private LocalDateTime startDate;

    @JsonIgnore
    @ManyToMany(mappedBy = "participants")
    private Set<ChatRoom> chatRooms;
}
