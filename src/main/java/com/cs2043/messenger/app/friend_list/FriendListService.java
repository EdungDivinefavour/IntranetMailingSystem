package com.cs2043.messenger.app.friend_list;

import com.cs2043.messenger.app.friend_list.models.FriendList;
import com.cs2043.messenger.app.friend_list.models.FriendListAddOrRemoveRequest;
import com.cs2043.messenger.app.user.UserRepository;
import com.cs2043.messenger.app.user.models.User;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record FriendListService(
        FriendListRepository friendListRepository,
        UserRepository userRepository
) {
    public void addFriend(FriendListAddOrRemoveRequest friendListAddOrRemoveRequest) {
        Optional<FriendList> existingFriend = friendListRepository.findSpecificFriendOfUser(
                friendListAddOrRemoveRequest.userId(), friendListAddOrRemoveRequest.friendId()
        );
        if(existingFriend.isPresent()) {
            throw new EntityExistsException("This friend already exists");
        }

        User user = userRepository.findById(friendListAddOrRemoveRequest.userId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        User friend = userRepository.findById(friendListAddOrRemoveRequest.friendId()).orElseThrow(() -> new EntityNotFoundException("User not found"));

        FriendList friendListRecord = FriendList.builder()
                .user(user)
                .friend(friend)
                .build();
        friendListRepository.save(friendListRecord);
    }

    public void removeFriend(FriendListAddOrRemoveRequest friendListAddOrRemoveRequest) {
        Optional<FriendList> existingFriend = friendListRepository.findSpecificFriendOfUser(
                friendListAddOrRemoveRequest.userId(), friendListAddOrRemoveRequest.friendId()
        );
        if(existingFriend.isEmpty()) {
            throw new EntityExistsException("This friend does not exist");
        }

        friendListRepository.delete(existingFriend.get());
    }

    List<FriendList> getAllFriendsForUser(Long userId) {
        return friendListRepository.findAllFriendsForUser(userId);
    }
}
