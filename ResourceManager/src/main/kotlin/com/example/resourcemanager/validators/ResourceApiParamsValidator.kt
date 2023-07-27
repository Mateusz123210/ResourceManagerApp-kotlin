package com.example.resourcemanager.validators

import com.example.resourcemanager.additionalTypes.ResourceType
import com.example.resourcemanager.exceptions.ArgumentNotGivenException
import com.example.resourcemanager.exceptions.InvalidArgumentException
import org.json.JSONException
import org.json.JSONObject

class ResourceApiParamsValidator {

    companion object{
        fun validateAddResourceParameters(name: String, userId: Int, type: ResourceType,
                                          metadata: String){
            if(name.isEmpty()){
                throw ArgumentNotGivenException("Name was not given!");
            }
            if(!StringValidator.checkIfContainsOnlyLettersAndDigits(name)){
                throw InvalidArgumentException("Resource name should consists only of letters and digits!");
            }
            UserApiParamsValidator.validateUserId(userId);
            if(metadata.isEmpty()){
                throw ArgumentNotGivenException("Metadata was not given!");
            }
            validateMetadata(metadata);
        }

        fun validateUpdateResourceNameParameters(id: Int, newName: String){
            validateResourceId(id);
            if(!StringValidator.checkIfContainsOnlyLettersAndDigits(newName)) {
                throw InvalidArgumentException("Resource name should consists only of letters and digits!");
            }
        }

        fun validateResourceId(id: Int){
            if(id <= 0) {
                throw InvalidArgumentException("Given resource id is invalid!");
            }
        }

        fun validateUpdateResourceMetadataParameters(id: Int, type: ResourceType , metadata: String){
            validateResourceId(id);
            validateResourceType(type);
            validateMetadata(metadata);
        }

        private fun validateResourceType(type: ResourceType?) {
            if (type == null) {
                throw ArgumentNotGivenException("Resource type was not given!");
            }
        }

        private fun validateMetadata(metadata: String){
            try{
                JSONObject(metadata);
            }catch(e: JSONException) {
                throw InvalidArgumentException("Metadata is not a valid json!");
            }
        }
    }
}