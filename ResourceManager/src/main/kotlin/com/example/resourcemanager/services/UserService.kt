package com.example.resourcemanager.services

import com.example.resourcemanager.additionalTypes.UserType
import com.example.resourcemanager.dtos.AddUserDTO
import com.example.resourcemanager.dtos.UpdateUserNickDTO
import com.example.resourcemanager.exceptions.ApplicationException
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

    fun addUser(addUserDTO: AddUserDTO): ResponseEntity<String> {
        val nick: String = addUserDTO.nick;
        val name: String = addUserDTO.name;
        val surname: String = addUserDTO.surname;
        val type: UserType = addUserDTO.type;
        UserApiParamsValidator.validateAddUserParameters(nick, name, surname, type);
        val currentDateTime: LocalDateTime = LocalDateTime.now();
        val userEntity = UserEntity(nick, name, surname,
            currentDateTime, currentDateTime, type);
        userRepository.save(userEntity);
        return ResponseEntity.ok("User was added!");
    }

    @Transactional
    fun deleteUser(id: Int, authorizedUserId: Int): ResponseEntity<String>{
        UserApiParamsValidator.validateUserId(authorizedUserId);
        UserApiParamsValidator.validateUserId(id);
        val authorizedUser: UserEntity = userRepository.findById(authorizedUserId).
        orElseThrow{ApplicationException("User was not deleted, because authorized user does not exist!")};
        if(authorizedUser.type == UserType.DEFAULT){
            throw ApplicationException("User is not permitted to delete users!");
        }
        val user: UserEntity = userRepository.findById(id).
        orElseThrow{ApplicationException("User was not deleted, because does not exist!")};
        val userResourceEntities: List<ResourceEntity>  = resourceRepository.findAllByUserId(user);
        for(resourceEntity in userResourceEntities){
            resourceRepository.deleteById(resourceEntity.id);
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok("User was deleted!");
    }

    @Transactional
    fun updateUserNick(updateUserNickDTO: UpdateUserNickDTO): ResponseEntity<String>{
        UserApiParamsValidator.checkUpdateUserNickParameters(updateUserNickDTO.id, updateUserNickDTO.newNick);
        val user: UserEntity = userRepository.findById(updateUserNickDTO.id)
            .orElseThrow{ApplicationException("Nick was not changed. User with this id does not exist!")};
        val currentDateTime: LocalDateTime = LocalDateTime.now();
        user.nick = updateUserNickDTO.newNick;
        user.modificationTime = currentDateTime;
        userRepository.save(user);
        return ResponseEntity.ok("User nick was changed!");
    }
}