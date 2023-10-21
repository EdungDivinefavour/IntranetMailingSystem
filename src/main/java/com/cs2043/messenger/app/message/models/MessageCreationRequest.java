package com.cs2043.messenger.app.message.models;

public record MessageCreationRequest(
        Long chatRoomId,
        Long senderId,
        Long receiverId,
        String text
) {
}
