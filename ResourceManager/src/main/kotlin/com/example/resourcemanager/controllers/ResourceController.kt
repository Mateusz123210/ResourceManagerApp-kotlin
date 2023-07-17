package com.example.resourcemanager.controllers

import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/resource")
@RequiredArgsConstructor
@RestController
class ResourceController {

    fun addUser(): ResponseEntity<String>{


        return ResponseEntity.status(200).body("a");
    }



}