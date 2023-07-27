package com.example.resourcemanager.services

import com.example.resourcemanager.additionalTypes.UserType
import com.example.resourcemanager.dtos.UserDTO
import com.example.resourcemanager.dtos.UserNickDTO
import com.example.resourcemanager.exceptions.AuthorizedUserNotFoundException
import com.example.resourcemanager.exceptions.UserNotFoundException
import com.example.resourcemanager.exceptions.UserNotPermittedException
import com.example.resourcemanager.models.ResourceEntity
import com.example.resourcemanager.models.UserEntity
import com.example.resourcemanager.repositories.ResourceRepository
import com.example.resourcemanager.repositories.UserRepository
import com.example.resourcemanager.validators.UserApiParamsValidator
import jakarta.transaction.Transactional
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@RequiredArgsConstructor
@Service
class UserService(val userRepository: UserRepository, val resourceRepository: ResourceRepository) {

    fun addUser(userDTO: UserDTO): ResponseEntity<String> {
        val nick: String = userDTO.nick;
        val name: String = userDTO.name;
        val surname: String = userDTO.surname;
        val type: UserType = userDTO.type;

        UserApiParamsValidator.validateAddUserParameters(nick, name, surname, type);

        val currentDateTime: LocalDateTime = LocalDateTime.now();
        val userEntity = UserEntity(nick, name, surname,
            currentDateTime, currentDateTime, type);

        userRepository.save(userEntity);

        return ResponseEntity.ok("User was added!");
    }

    @Transactional
    fun deleteUser(id: Int, authorizedUserId: Int): ResponseEntity<String>{
        UserApiParamsValidator.validateUserId(id, authorizedUserId);

        val authorizedUser: UserEntity = userRepository.findById(authorizedUserId).
            orElseThrow{AuthorizedUserNotFoundException("User was not deleted, because authorized user does not exist!")};

        if(authorizedUser.type == UserType.DEFAULT){
            throw UserNotPermittedException("User is not permitted to delete users!");
        }

        val user: UserEntity = userRepository.findById(id).
            orElseThrow{UserNotFoundException("User was not deleted, because does not exist!")};
        val userResourceEntities: List<ResourceEntity>  = resourceRepository.findAllByUserId(user);

        for(resourceEntity in userResourceEntities){
            resourceRepository.deleteById(resourceEntity.id);
        }
        userRepository.deleteById(id);

        return ResponseEntity.ok("User was deleted!");
    }

    @Transactional
    fun updateUserNick(userNickDTO: UserNickDTO): ResponseEntity<String>{
        val userId: Int = userNickDTO.userId;
        val newNick: String = userNickDTO.newNick;

        UserApiParamsValidator.checkUpdateUserNickParameters(userId, newNick);

        val user: UserEntity = userRepository.findById(userId)
            .orElseThrow{UserNotFoundException("Nick was not changed. User with this id does not exist!")};
        val currentDateTime: LocalDateTime = LocalDateTime.now();

        user.nick = newNick;
        user.modificationTime = currentDateTime;
        userRepository.save(user);

        return ResponseEntity.ok("User nick was changed!");
    }
}