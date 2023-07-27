package com.example.resourcemanager.validators

import com.example.resourcemanager.additionalTypes.UserType
import com.example.resourcemanager.exceptions.ArgumentNotGivenException
import com.example.resourcemanager.exceptions.InvalidArgumentException

class UserApiParamsValidator {

    companion object{
        fun validateAddUserParameters(nick: String, name: String, surname: String,
        type: UserType) {
            if(nick.isEmpty() or name.isEmpty() or surname.isEmpty()) {
                throw ArgumentNotGivenException("Minimum one of arguments was not given!");
            }
            if(!StringValidator.checkIfContainsOnlyLettersAndDigits(nick)){
                throw InvalidArgumentException("Nick should consists of letters and numbers only!");
            }
            if(!StringValidator.checkIfContainsOnlyLetters(name) or
                    !StringValidator.checkIfContainsOnlyLetters(surname)) {
                throw InvalidArgumentException("Name and surname should consist of letters only!");
            }
        }

        fun validateUserId(id: Int){
            if(id <= 0) {
                throw InvalidArgumentException("Given user id is invalid!");
            }
        }

        fun validateUserId(id: Int, authorizedUserId: Int){
            validateUserId(id);
            if(id <= 0) {
                throw InvalidArgumentException("Given authorized user id is invalid!")
            }
        }

        fun checkUpdateUserNickParameters(id: Int, nick: String){
            if(id <= 0 ) {
                throw InvalidArgumentException("Given user id is invalid!");
            }
            if(nick.isEmpty()){
                throw ArgumentNotGivenException("Nick was not given!");
            }
            if(!StringValidator.checkIfContainsOnlyLettersAndDigits(nick)){
                throw InvalidArgumentException("Nick should consists only of letters and digits!");
            }
        }
    }
}