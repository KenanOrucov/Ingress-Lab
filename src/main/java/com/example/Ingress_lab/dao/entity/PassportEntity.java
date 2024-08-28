package com.example.Ingress_lab.dao.entity;
import com.example.Ingress_lab.model.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldNameConstants
@Entity
@Table(name = "passports")
public class PassportEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String passportNumber;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private String country;
    @Enumerated(STRING)
    private Status status;
    @OneToOne(
            mappedBy = "passport",
            cascade = ALL,
            fetch = LAZY
    )
    @ToString.Exclude
    private GuideEntity guide;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
