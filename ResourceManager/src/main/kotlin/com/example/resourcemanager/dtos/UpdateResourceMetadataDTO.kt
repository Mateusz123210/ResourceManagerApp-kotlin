package com.example.resourcemanager.dtos

import com.example.resourcemanager.additionalTypes.ResourceType

data class UpdateResourceMetadataDTO(val id: Int, val metadataType: ResourceType,
    val metadata: String)
