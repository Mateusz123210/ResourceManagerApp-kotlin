package com.example.resourcemanager.services

import com.example.resourcemanager.dtos.AddResourceDTO
import com.example.resourcemanager.dtos.UpdateResourceMetadataDTO
import com.example.resourcemanager.dtos.UpdateResourceNameDTO
import com.example.resourcemanager.repositories.ResourceRepository
import com.example.resourcemanager.repositories.UserRepository
import jakarta.transaction.Transactional
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@RequiredArgsConstructor
@Service
class ResourceService(val resourceRepository: ResourceRepository, val userRepository: UserRepository) {

    @Transactional
    fun addResource(addResourceDTO: AddResourceDTO): ResponseEntity<String>{





        return ResponseEntity.status(200).body("") //to delete
    }

    @Transactional
    fun deleteResource(id: Int, authorizedUserId: Int): ResponseEntity<String>{





        return ResponseEntity.status(200).body("") //to delete
    }

    @Transactional
    fun changeResourceName(updateResourceNameDTO: UpdateResourceNameDTO):
            ResponseEntity<String>{





        return ResponseEntity.status(200).body("") //to delete
    }

    @Transactional
    fun changeResourceMetadata(updateResourceMetadataDTO: UpdateResourceMetadataDTO):
            ResponseEntity<String>{



        return ResponseEntity.status(200).body("") //to delete
    }
}