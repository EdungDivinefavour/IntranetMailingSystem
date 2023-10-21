package com.cs2043.messenger.app.company.models;

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
public class Company {
    @Id
    @SequenceGenerator(name = "company_sequence", sequenceName = "company_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_sequence")
    private Long id;

    private String name;
    private String address;
    private int numberOfStaff;

    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime creationTimeStamp;
}
