package com.example.resourcemanager.repositories

import com.example.resourcemanager.models.ResourceEntity
import com.example.resourcemanager.models.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ResourceRepository: JpaRepository<ResourceEntity, Int> {
    fun findAllByUserId(userEntity: UserEntity): List<ResourceEntity>;
}