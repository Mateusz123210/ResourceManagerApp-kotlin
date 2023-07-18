package com.example.resourcemanager.exceptions

class ApplicationException(message: String): RuntimeException(message) {

    @Override
    fun ApplicationException(message: String){
        RuntimeException(message);
    }

}