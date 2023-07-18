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
    var id: Int = 0;

    @Column(nullable = false)
    var nick: String? = null;

    @Column(nullable = false)
    var name: String? = null;

    @Column(nullable = false)
    var surname: String? = null;

    @Column(nullable = false)
    var creationTime: LocalDateTime? = null;

    @Column(nullable = false)
    var modificationTime: LocalDateTime? = null;

    @Enumerated(EnumType.ORDINAL)
    var type: UserType? = null;
}