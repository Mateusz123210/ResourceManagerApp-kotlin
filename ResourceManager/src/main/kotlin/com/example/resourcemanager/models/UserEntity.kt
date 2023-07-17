package com.example.resourcemanager.models

import com.example.resourcemanager.additionalTypes.UserType
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
@Table(name = "users")
class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Int? = null;

    @Column(nullable = false)
    private var nick: String? = null;

    @Column(nullable = false)
    private var name: String? = null;

    @Column(nullable = false)
    private var surname: String? = null;

    @Column(nullable = false)
    private var creationTime: LocalDateTime? = null;

    @Column(nullable = false)
    private var modificationTime: LocalDateTime? = null;

    @Enumerated(EnumType.ORDINAL)
    private var type: UserType? = null;
}