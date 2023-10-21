package com.cs2043.messenger.app.friend_list.models;

public record FriendListAddOrRemoveRequest(
        Long userId,
        Long friendId
) {
}
