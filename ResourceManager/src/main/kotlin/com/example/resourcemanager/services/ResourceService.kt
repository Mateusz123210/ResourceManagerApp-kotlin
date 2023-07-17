package com.example.resourcemanager.services

import com.example.resourcemanager.repositories.ResourceRepository
import com.example.resourcemanager.repositories.UserRepository
import jakarta.transaction.Transactional
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service


@RequiredArgsConstructor
@Service
class ResourceService {

    private val resourceRepository: ResourceRepository? = null;
    private val userRepository: UserRepository? = null;

//    @Transactional
//    fun addResource()





}