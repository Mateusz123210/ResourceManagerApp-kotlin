package com.example.resourcemanager.validators

class StringValidator {

    companion object{
        fun checkIfContainsOnlyLetters(str: String): Boolean{
            for(i in str){
                if(!i.isLetter()){
                    return false;
                }
            }
            return true;
        }

        fun checkIfContainsOnlyLettersAndDigits(str: String):Boolean{
            for(i in str){
                if(!i.isLetter() and !i.isDigit()){
                    return false;
                }
            }
            return true;
        }
    }
}