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
    var nick: String = "";

    @Column(nullable = false)
    var name: String = "";

    @Column(nullable = false)
    var surname: String = "";

    @Column(nullable = false)
    var creationTime: LocalDateTime = LocalDateTime.now();

    @Column(nullable = false)
    var modificationTime: LocalDateTime? = creationTime;

    @Enumerated(EnumType.ORDINAL)
    var type: UserType = UserType.DEFAULT;

    constructor(nick: String, name: String, surname: String,
                creationTime: LocalDateTime, modificationTime: LocalDateTime, type: UserType){
        this.nick = nick;
        this.name = name;
        this.surname = surname;
        this.creationTime = creationTime;
        this.modificationTime = modificationTime;
        this.type = type;
    }

    constructor();
}