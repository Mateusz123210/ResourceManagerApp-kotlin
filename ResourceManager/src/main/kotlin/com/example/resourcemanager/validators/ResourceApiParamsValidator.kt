package com.example.resourcemanager.validators

import com.example.resourcemanager.additionalTypes.ResourceType
import com.example.resourcemanager.exceptions.ApplicationException
import org.json.JSONException
import org.json.JSONObject

class ResourceApiParamsValidator {

    companion object{
        fun validateAddResourceParameters(name: String, userId: Int, type: ResourceType,
                                          metadata: String){
            if(name.isEmpty()){
                throw ApplicationException("Name was not given!");
            }
            if(!StringValidator.checkIfContainsOnlyLettersAndDigits(name)){
                throw ApplicationException("Resource name should consists only of letters and digits!");
            }
            UserApiParamsValidator.validateUserId(userId);
            if(metadata.isEmpty()){
                throw ApplicationException("Metadata was not given!");
            }
            validateMetadata(metadata);
        }

        fun validateUpdateResourceNameParameters(id: Int, newName: String){
            validateResourceId(id);
            if(!StringValidator.checkIfContainsOnlyLettersAndDigits(newName)) {
                throw ApplicationException("Resource name should consists only of letters and digits!");
            }
        }

        fun validateResourceId(id: Int){
            if(id <= 0) {
                throw ApplicationException("Given resource id is invalid!");
            }
        }

        fun validateUpdateResourceMetadataParameters(id: Int, type: ResourceType , metadata: String){
            validateResourceId(id);
            validateMetadata(metadata);
        }

        fun validateMetadata(metadata: String){
            try{
                JSONObject(metadata);
            }catch(e: JSONException) {
                throw ApplicationException("Metadata is not a valid json!");
            }
        }






    }
}