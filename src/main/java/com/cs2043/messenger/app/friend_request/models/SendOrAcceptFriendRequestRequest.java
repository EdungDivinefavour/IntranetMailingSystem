package com.cs2043.messenger.app.friend_request.models;

public record SendOrAcceptFriendRequestRequest(
        Long senderId,
        Long receiverId
) {
}
