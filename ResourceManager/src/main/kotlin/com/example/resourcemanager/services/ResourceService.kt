package com.example.resourcemanager.services

import com.example.resourcemanager.additionalTypes.ResourceType
import com.example.resourcemanager.dtos.ResourceDTO
import com.example.resourcemanager.dtos.ResourceMetadataDTO
import com.example.resourcemanager.dtos.ResourceNameDTO
import com.example.resourcemanager.exceptions.*
import com.example.resourcemanager.models.ResourceEntity
import com.example.resourcemanager.models.UserEntity
import com.example.resourcemanager.repositories.ResourceRepository
import com.example.resourcemanager.repositories.UserRepository
import com.example.resourcemanager.validators.ResourceApiParamsValidator
import com.example.resourcemanager.validators.UserApiParamsValidator
import jakarta.transaction.Transactional
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@RequiredArgsConstructor
@Service
class ResourceService(val resourceRepository: ResourceRepository, val userRepository: UserRepository) {

    @Transactional
    fun addResource(resourceDTO: ResourceDTO): ResponseEntity<String>{
        val name: String = resourceDTO.name;
        val userId: Int = resourceDTO.userId;
        val type: ResourceType = resourceDTO.type;
        val metadata: String = resourceDTO.metadata;

        ResourceApiParamsValidator.validateAddResourceParameters(name, userId, type, metadata);

        val user: UserEntity = userRepository.findById(userId)
            .orElseThrow{
                UserNotFoundException("Resource was not added. User with this id does not exist!")
            };
        val currentDateTime: LocalDateTime = LocalDateTime.now();
        val resourceEntity = ResourceEntity(name, currentDateTime, currentDateTime,
            user, type, metadata);

        resourceRepository.save(resourceEntity);

        return ResponseEntity.ok("Resource was added!");
    }

    @Transactional
    fun deleteResource(id: Int, authorizedUserId: Int): ResponseEntity<String>{
        UserApiParamsValidator.validateUserId(authorizedUserId);

        val authorizedUser: UserEntity = userRepository.findById(authorizedUserId)
            .orElseThrow {AuthorizedUserNotFoundException("Resource was not deleted. Authorized user does not exist!")};

        ResourceApiParamsValidator.validateResourceId(id);

        val resource: ResourceEntity = resourceRepository.findById(id)
            .orElseThrow {ResourceNotFoundException("Resource was not deleted. Resource with this id does not exist!")};

        if(resource.userId != authorizedUser){
            throw UserNotPermittedException("Resource was not deleted. Resource does not belong to this user!");
        }
        resourceRepository.deleteById(id);

        return ResponseEntity.ok("Resource was deleted!");
    }

    @Transactional
    fun changeResourceName(resourceNameDTO: ResourceNameDTO):
            ResponseEntity<String>{
        val id: Int = resourceNameDTO.resourceId;
        val  newName: String = resourceNameDTO.newName;

        ResourceApiParamsValidator.validateUpdateResourceNameParameters(id, newName);

        val resource: ResourceEntity = resourceRepository.findById(id).orElseThrow{
            ResourceNotFoundException("Resource name was not changed. Resource with this id does not exist!")};

        resource.name = newName;
        resource.modificationTime = LocalDateTime.now();
        resourceRepository.save(resource);

        return ResponseEntity.ok("Resource name was changed!");
    }

    @Transactional
    fun changeResourceMetadata(resourceMetadataDTO: ResourceMetadataDTO):
            ResponseEntity<String>{
        val id: Int  = resourceMetadataDTO.resourceId;
        val type: ResourceType = resourceMetadataDTO.metadataType;
        val metadata: String = resourceMetadataDTO.metadata;

        ResourceApiParamsValidator.validateUpdateResourceMetadataParameters(id, type, metadata);

        val resource: ResourceEntity = resourceRepository.findById(id).orElseThrow{
            ResourceNotFoundException("Resource name was not changed. Resource with this id does not exist!")};

        resource.type = type;
        resource.metadata = metadata;
        resource.modificationTime = LocalDateTime.now();
        resourceRepository.save(resource);

        return ResponseEntity.ok("Resource metadata was changed!");
    }
}