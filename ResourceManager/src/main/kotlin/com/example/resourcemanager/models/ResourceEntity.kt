package com.example.resourcemanager.models

import com.example.resourcemanager.additionalTypes.ResourceType
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDateTime

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "resources")
class ResourceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Int? = null;

    @Column(nullable = false)
    private var name: String? = null;

    @Column(nullable = false)
    private var creationTime: LocalDateTime? = null;

    @Column(nullable = false)
    private var modificationTime: LocalDateTime? = null;

    @ManyToOne
    @JoinColumn(nullable = false)
    private var userId: UserEntity? = null;

    @Enumerated(EnumType.ORDINAL)
    private var type: ResourceType? = null;

    @Column(nullable = false)
    private var metadata: String? = null;
}