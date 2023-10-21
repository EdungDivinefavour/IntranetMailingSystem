package com.cs2043.messenger.app.friend_request;

import com.cs2043.messenger.app.friend_request.models.FriendRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long>{
    @Query("SELECT r " +
            "FROM FriendRequest r " +
            "WHERE r.sender.id = ?1")
    List<FriendRequest> findAllBySenderId(Long senderId);

    @Query("SELECT r " +
            "FROM FriendRequest r " +
            "WHERE r.receiver.id = ?1")
    List<FriendRequest> findAllByReceiverId(Long receiverId);

    @Query("SELECT r " +
            "FROM FriendRequest r " +
            "WHERE r.sender.id = :senderId AND r.receiver.id = :receiverId"
    )
    Optional<FriendRequest> getAlreadyExistingRequest(
            @Param("senderId") Long senderId,
            @Param("receiverId") Long receiverId
    );
}
