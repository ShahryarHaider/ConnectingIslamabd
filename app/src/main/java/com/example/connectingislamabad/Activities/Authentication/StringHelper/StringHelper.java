package com.example.connectingislamabad.Activities.Authentication.StringHelper;

public class StringHelper {

    //Validate Email
    public static boolean regexEmailValidationPattern(String email){
        //Set Pattern
        String regex = "([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})";

        if(email.matches(regex)){
            return true;
        }
        return false;
    }
    //End For Regular Expression for Email
}
