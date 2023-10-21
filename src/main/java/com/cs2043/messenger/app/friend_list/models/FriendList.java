package com.cs2043.messenger.app.friend_list.models;

import com.cs2043.messenger.app.user.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FriendList {
    @Id
    @SequenceGenerator(name = "friend_list_sequence", sequenceName = "friend_list_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "friend_list_sequence")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id") // Specify foreign key column name and referenced column name
    User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "friend_id", referencedColumnName = "id") // Specify foreign key column name and referenced column name
    User friend;
}
