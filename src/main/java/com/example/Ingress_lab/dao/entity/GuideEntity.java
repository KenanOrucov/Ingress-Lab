package com.example.Ingress_lab.dao.entity;
import com.example.Ingress_lab.model.enums.GuideStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

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
@Table(name = "guides")
public class GuideEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    @Enumerated(STRING)
    private GuideStatus status;

    @OneToOne(cascade = ALL, fetch = LAZY)
    @JoinColumn(name = "passport_id")
    private PassportEntity passport;

    @ManyToMany(cascade = ALL)
    @JoinTable(
            name = "guides_tours",
            joinColumns = @JoinColumn(name = "guide_id"),
            inverseJoinColumns = @JoinColumn(name = "tour_id")
    )
    private Set<TourEntity> tours;


    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuideEntity that = (GuideEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
