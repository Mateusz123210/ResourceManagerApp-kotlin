package com.example.resourcemanager.controllers

import com.example.resourcemanager.dtos.UserDTO
import com.example.resourcemanager.dtos.UserNickDTO
import com.example.resourcemanager.services.UserService
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/user")
@RequiredArgsConstructor
@RestController
class UserController(private val userService: UserService) {

    @PostMapping
    fun addUser(@RequestBody userDTO: UserDTO): ResponseEntity<String>{
        return userService.addUser(userDTO);
    }

    @DeleteMapping
    fun deleteUser(@RequestParam userId: Int, @RequestParam authorizedUserId: Int):
            ResponseEntity<String>{
        return userService.deleteUser(userId, authorizedUserId);
    }

    @PutMapping(value = ["/update-nick"])
    fun updateUserNick(@RequestBody userNickDTO: UserNickDTO):
            ResponseEntity<String>{
        return userService.updateUserNick(userNickDTO);
    }
}