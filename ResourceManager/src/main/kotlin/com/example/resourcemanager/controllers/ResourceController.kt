package com.example.resourcemanager.controllers

import com.example.resourcemanager.dtos.AddResourceDTO
import com.example.resourcemanager.dtos.UpdateResourceMetadataDTO
import com.example.resourcemanager.dtos.UpdateResourceNameDTO
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
    fun addResource(@RequestBody addResourceDTO: AddResourceDTO):
            ResponseEntity<String>{
        return resourceService.addResource(addResourceDTO);
    }

    @DeleteMapping
    fun deleteResource(@RequestParam id: Int, @RequestParam authorizedUserId: Int):
            ResponseEntity<String>{
        return resourceService.deleteResource(id, authorizedUserId);
    }

    @PutMapping(value = ["/update-name"])
    fun updateResourceName(@RequestBody updateResourceNameDTO: UpdateResourceNameDTO):
            ResponseEntity<String>{
        return resourceService.changeResourceName(updateResourceNameDTO);
    }

    @PutMapping(value = ["/update-metadata"])
    fun updateResourceMetadata(@RequestBody updateResourceMetadataDTO: UpdateResourceMetadataDTO):
            ResponseEntity<String>{
        return resourceService.changeResourceMetadata(updateResourceMetadataDTO);
    }
}