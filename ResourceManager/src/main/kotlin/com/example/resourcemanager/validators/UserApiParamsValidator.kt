package com.example.resourcemanager.validators

import com.example.resourcemanager.additionalTypes.UserType
import com.example.resourcemanager.exceptions.ApplicationException

class UserApiParamsValidator {

    companion object{
        fun validateAddUserParameters(nick: String, name: String, surname: String,
        type: UserType) {
            if(nick.isEmpty() or name.isEmpty() or surname.isEmpty()) {
                throw ApplicationException("Minimum one of arguments was not given!");
            }
            if(!StringValidator.checkIfContainsOnlyLettersAndDigits(nick)){
                throw ApplicationException("Nick should consists of letters and numbers only!");
            }
            if(!StringValidator.checkIfContainsOnlyLetters(name) or
                    !StringValidator.checkIfContainsOnlyLetters(surname)) {
                throw ApplicationException("Name and surname should consist of letters only!");
            }
        }

        fun validateUserId(id: Int){
            if(id <= 0) {
                throw ApplicationException("Given user id is invalid!");
            }
        }

        fun checkUpdateUserNickParameters(id: Int, nick: String){
            if(id <= 0 ) {
                throw ApplicationException("Given user id is invalid!");
            }
            if(nick.isEmpty()){
                throw ApplicationException("Nick was not given!");
            }
            if(!StringValidator.checkIfContainsOnlyLettersAndDigits(nick)){
                throw ApplicationException("Nick should consists only of letters and digits!");
            }
        }
    }
}