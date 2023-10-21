package com.cs2043.messenger.app.message;

import com.cs2043.messenger.app.chat_room.models.ChatRoom;
import com.cs2043.messenger.app.message.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM Message m WHERE m.chatRoom.id = :chatRoomId")
    List<Message> getAllMessagesForChatRoom(@Param("chatRoomId") Long chatRoomId);

}
