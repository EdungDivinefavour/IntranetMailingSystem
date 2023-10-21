package com.cs2043.messenger.app.chat_room;

import com.cs2043.messenger.app.chat_room.models.ChatRoom;
import com.cs2043.messenger.app.message.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    @Query("SELECT c FROM ChatRoom c JOIN c.participants p WHERE p.id = :userId")
    List<ChatRoom> getAllChatRoomsForUser(@Param("userId") Long userId);
}
