package com.example.resourcemanager.services

import com.example.resourcemanager.additionalTypes.ResourceType
import com.example.resourcemanager.dtos.AddResourceDTO
import com.example.resourcemanager.dtos.UpdateResourceMetadataDTO
import com.example.resourcemanager.dtos.UpdateResourceNameDTO
import com.example.resourcemanager.exceptions.ApplicationException
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
    fun addResource(addResourceDTO: AddResourceDTO): ResponseEntity<String>{
        val name: String = addResourceDTO.name;
        val userId: Int = addResourceDTO.userId;
        val type: ResourceType = addResourceDTO.type;
        val metadata: String = addResourceDTO.metadata;
        ResourceApiParamsValidator.validateAddResourceParameters(name, userId, type, metadata);
        val user: UserEntity = userRepository.findById(addResourceDTO.userId)
            .orElseThrow{
                ApplicationException("Resource was not added. User with this id does not exist!")};
        val currentDateTime: LocalDateTime = LocalDateTime.now();
        val resourceEntity: ResourceEntity = ResourceEntity(addResourceDTO.name, currentDateTime, currentDateTime,
            user, addResourceDTO.type, addResourceDTO.metadata);
        resourceRepository.save(resourceEntity);
        return ResponseEntity.ok("Resource was added!");
    }

    @Transactional
    fun deleteResource(id: Int, authorizedUserId: Int): ResponseEntity<String>{
        UserApiParamsValidator.validateUserId(authorizedUserId);
        val authorizedUser: UserEntity = userRepository.findById(authorizedUserId)
            .orElseThrow {ApplicationException("Resource was not deleted. Authorized user does not exist!")};
        ResourceApiParamsValidator.validateResourceId(id);
        val resource: ResourceEntity = resourceRepository.findById(id)
            .orElseThrow {ApplicationException("Resource was not deleted. Resource with this id does not exist!")};
        if(resource.userId != authorizedUser){
            throw ApplicationException("Resource was not deleted. Resource does not belong to this user!");
        }
        resourceRepository.deleteById(id);
        return ResponseEntity.ok("Resource was deleted!");
    }

    @Transactional
    fun changeResourceName(updateResourceNameDTO: UpdateResourceNameDTO):
            ResponseEntity<String>{
        val id: Int = updateResourceNameDTO.id;
        val  newName: String = updateResourceNameDTO.newName;
        ResourceApiParamsValidator.validateUpdateResourceNameParameters(id, newName);
        val resource: ResourceEntity = resourceRepository.findById(id).orElseThrow{
        ApplicationException("Resource name was not changed. Resource with this id does not exist!")};
        resource.name = newName;
        resource.modificationTime = LocalDateTime.now();
        resourceRepository.save(resource);
        return ResponseEntity.ok("Resource name was changed!");
    }

    @Transactional
    fun changeResourceMetadata(updateResourceMetadataDTO: UpdateResourceMetadataDTO):
            ResponseEntity<String>{
        val id: Int  = updateResourceMetadataDTO.id;
        val type: ResourceType = updateResourceMetadataDTO.metadataType;
        val metadata: String = updateResourceMetadataDTO.metadata;
        ResourceApiParamsValidator.validateUpdateResourceMetadataParameters(id, type, metadata);
        val resource: ResourceEntity = resourceRepository.findById(id).orElseThrow{
            ApplicationException("Resource name was not changed. Resource with this id does not exist!")};
        resource.type = type;
        resource.metadata = metadata;
        resource.modificationTime = LocalDateTime.now();
        resourceRepository.save(resource);
        return ResponseEntity.ok("Resource metadata was changed!");
    }
}