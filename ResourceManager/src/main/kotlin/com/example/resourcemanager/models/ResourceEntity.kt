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
class ResourceEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0;

    @Column(nullable = false)
    var name: String = "";

    @Column(nullable = false)
    var creationTime: LocalDateTime = LocalDateTime.now();

    @Column(nullable = false)
    var modificationTime: LocalDateTime = creationTime;

    @ManyToOne
    @JoinColumn(nullable = false)
    var userId: UserEntity = UserEntity();

    @Enumerated(EnumType.ORDINAL)
    var type: ResourceType = ResourceType.RESEARCH;

    @Column(nullable = false)
    var metadata: String = "";

    constructor(name: String, creationTime: LocalDateTime, modificationTime: LocalDateTime,
        userId: UserEntity, type: ResourceType, metadata: String){
        this.name = name;
        this.creationTime = creationTime;
        this.modificationTime = modificationTime;
        this.userId = userId;
        this.type = type;
        this.metadata = metadata;
    }

    constructor();
}