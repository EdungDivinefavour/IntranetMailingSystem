package com.cs2043.messenger.app.branch;

import com.cs2043.messenger.app.branch.models.Branch;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long>{
    @Query("SELECT b " +
            "FROM Branch b " +
            "WHERE b.name = ?1")
    Optional<Branch> findByName(String name);
}
