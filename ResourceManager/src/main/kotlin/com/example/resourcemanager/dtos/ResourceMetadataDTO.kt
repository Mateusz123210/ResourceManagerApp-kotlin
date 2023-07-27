package com.example.resourcemanager.dtos

import com.example.resourcemanager.additionalTypes.ResourceType

data class ResourceMetadataDTO(val resourceId: Int, val metadataType: ResourceType,
                               val metadata: String)
