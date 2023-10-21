package com.cs2043.messenger.app.company;

import com.cs2043.messenger.app.branch.models.Branch;
import com.cs2043.messenger.app.company.models.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
    @Query("SELECT c " +
            "FROM Company c " +
            "WHERE c.name = ?1")
    Optional<Company> findByName(String name);
}
