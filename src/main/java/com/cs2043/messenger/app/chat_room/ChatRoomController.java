package com.cs2043.messenger.app.chat_room;

import com.cs2043.messenger.app.chat_room.models.ChatRoom;
import com.cs2043.messenger.app.message.models.Message;
import com.cs2043.messenger.app.message.models.MessageCreationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/chatroom")
public record ChatRoomController(ChatRoomService chatRoomService) {
    @GetMapping()
    public List<ChatRoom> getAllChatRoomsForUser(@RequestParam("userId") Long userId) {
        log.info("wants to get all chatroom for user {}", userId);
        return chatRoomService.getAllChatRoomsForUser(userId);
    }

    @GetMapping(path = "/message")
    public List<Message> getAllMessagesForChatRoom(@RequestParam("chatRoomId") Long chatRoomId) {
        log.info("wants to get all messages for chatroom {}", chatRoomId);
        return chatRoomService.getAllMessagesForChatRoom(chatRoomId);
    }

    @PostMapping(path = "/message")
    public void sendMessage(@RequestBody MessageCreationRequest messageCreationRequest) {
        log.info("wants to send message with body {}", messageCreationRequest);
        chatRoomService.sendMessage(messageCreationRequest);
    }
}
