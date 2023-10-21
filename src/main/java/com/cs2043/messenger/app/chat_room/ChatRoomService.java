package com.cs2043.messenger.app.chat_room;

import com.cs2043.messenger.app.chat_room.models.ChatRoom;
import com.cs2043.messenger.app.message.models.Message;
import com.cs2043.messenger.app.message.models.MessageCreationRequest;
import com.cs2043.messenger.app.message.MessageRepository;
import com.cs2043.messenger.app.user.UserRepository;
import com.cs2043.messenger.app.user.models.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record ChatRoomService (
        ChatRoomRepository chatRoomRepository,
        MessageRepository messageRepository,
        UserRepository userRepository
){
    public List<ChatRoom> getAllChatRoomsForUser(Long userId) {
        return chatRoomRepository.getAllChatRoomsForUser(userId);
    }

    public List<Message> getAllMessagesForChatRoom(Long chatRoomId) {
        return messageRepository.getAllMessagesForChatRoom(chatRoomId);
    }

    public void sendMessage(MessageCreationRequest messageCreationRequest) {
        User sender = userRepository.findById(messageCreationRequest.senderId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        User receiver = userRepository.findById(messageCreationRequest.receiverId()).orElseThrow(() -> new EntityNotFoundException("User not found"));

        ChatRoom chatRoom;
        if(messageCreationRequest.chatRoomId() == null) {
            chatRoom = ChatRoom.builder()
                    .participants(List.of(sender, receiver))
                    .build();
            chatRoomRepository.save(chatRoom);
        } else {
            chatRoom = chatRoomRepository.findById(messageCreationRequest.chatRoomId()).get();
        }

        Message message = Message.builder()
                .chatRoom(chatRoom)
                .sender(sender)
                .receiver(receiver)
                .text(messageCreationRequest.text())
                .build();

        messageRepository.save(message);
    }
}
