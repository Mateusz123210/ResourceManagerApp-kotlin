package com.example.resourcemanager.dtos

import com.example.resourcemanager.additionalTypes.UserType

data class UserDTO(val nick: String, val name: String,
                   val surname: String, val type: UserType)
