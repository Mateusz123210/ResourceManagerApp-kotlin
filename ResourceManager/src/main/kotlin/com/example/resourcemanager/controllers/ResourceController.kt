package com.example.resourcemanager.controllers

import com.example.resourcemanager.dtos.ResourceDTO
import com.example.resourcemanager.dtos.ResourceMetadataDTO
import com.example.resourcemanager.dtos.ResourceNameDTO
import com.example.resourcemanager.services.ResourceService
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/resource")
@RequiredArgsConstructor
@RestController
class ResourceController(private val resourceService: ResourceService) {

    @PostMapping
    fun addResource(@RequestBody resourceDTO: ResourceDTO):
            ResponseEntity<String>{
        return resourceService.addResource(resourceDTO);
    }

    @DeleteMapping
    fun deleteResource(@RequestParam resourceId: Int, @RequestParam authorizedUserId: Int):
            ResponseEntity<String>{
        return resourceService.deleteResource(resourceId, authorizedUserId);
    }

    @PutMapping(value = ["/update-name"])
    fun updateResourceName(@RequestBody resourceNameDTO: ResourceNameDTO):
            ResponseEntity<String>{
        return resourceService.changeResourceName(resourceNameDTO);
    }

    @PutMapping(value = ["/update-metadata"])
    fun updateResourceMetadata(@RequestBody resourceMetadataDTO: ResourceMetadataDTO):
            ResponseEntity<String>{
        return resourceService.changeResourceMetadata(resourceMetadataDTO);
    }
}