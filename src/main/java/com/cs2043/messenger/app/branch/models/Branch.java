package com.cs2043.messenger.app.branch.models;

import com.cs2043.messenger.app.company.models.Company;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Branch {
    @Id
    @SequenceGenerator(name = "branch_sequence", sequenceName = "branch_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "branch_sequence")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id") // Specify foreign key column name and referenced column name
    private Company company;

    private String name;
    private String address;

    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime creationTimeStamp;
}
