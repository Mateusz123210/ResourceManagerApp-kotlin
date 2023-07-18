package com.example.resourcemanager.services

import com.example.resourcemanager.dtos.AddUserDTO
import com.example.resourcemanager.dtos.UpdateUserNickDTO
import com.example.resourcemanager.repositories.ResourceRepository
import com.example.resourcemanager.repositories.UserRepository
import jakarta.transaction.Transactional
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@RequiredArgsConstructor
@Service
class UserService {

    private val userRepository: UserRepository? = null;
    private val resourceRepository: ResourceRepository? = null;

    fun addUser(addUserDTO: AddUserDTO): ResponseEntity<String> {






        return ResponseEntity.status(200).body("") //to delete
    }

    @Transactional
    fun deleteUser(id: Int, authorizedUserId: Int): ResponseEntity<String>{



        return ResponseEntity.status(200).body("") //to delete
    }

    @Transactional
    fun updateUserNick(updateUserNickDTO: UpdateUserNickDTO): ResponseEntity<String>{





        return ResponseEntity.status(200).body("") //to delete
    }
}