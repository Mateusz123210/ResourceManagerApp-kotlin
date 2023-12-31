package com.example.resourcemanager.dtos

import com.example.resourcemanager.additionalTypes.ResourceType

data class AddResourceDTO(val name: String, val userId: Int,
                          val type: ResourceType, val metadata: String)
