package com.example.resourcemanager.controllers

import com.example.resourcemanager.dtos.AddUserDTO
import com.example.resourcemanager.dtos.UpdateUserNickDTO
import com.example.resourcemanager.services.UserService
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/user")
@RequiredArgsConstructor
@RestController
class UserController(private val userService: UserService) {

    @PostMapping
    fun addUser(@RequestBody addUserDTO: AddUserDTO): ResponseEntity<String>{
        return userService.addUser(addUserDTO);
    }

    @DeleteMapping
    fun deleteUser(@RequestParam id: Int, @RequestParam authorizedUserId: Int):
            ResponseEntity<String>{
        return userService.deleteUser(id, authorizedUserId);
    }

    @PutMapping(value = ["/update-nick"])
    fun updateUserNick(@RequestBody updateUserNickDTO: UpdateUserNickDTO):
            ResponseEntity<String>{
        return userService.updateUserNick(updateUserNickDTO);
    }
}