package com.cs2043.messenger.app.friend_list;

import com.cs2043.messenger.app.friend_list.models.FriendList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendListRepository extends JpaRepository<FriendList, Long>{

    @Query("SELECT f FROM FriendList f WHERE f.user.id = :userId AND f.friend.id = :friendId")
    Optional<FriendList> findSpecificFriendOfUser(
            @Param("userId") Long userId,
            @Param("friendId") Long friendId
    );


    @Query("SELECT f " +
            "FROM FriendList f " +
            "WHERE f.user.id = ?1")
    List<FriendList> findAllFriendsForUser(Long userId);
}
