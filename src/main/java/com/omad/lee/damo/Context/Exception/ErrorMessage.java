package com.omad.lee.damo.Context.Exception;

public enum ErrorMessage {

    MISSING_REQUIRED_FIELD("Xammasini doldir xarap"),
    RECORD_ALREADY_EXISTS("Already exist qaytadan gor"),
    INTERNAL_SERVER_ERROR("Internal serveringda gap bor"),
    NO_RECORD_FOUND("yech narsa tapilmadi"),
    AUTHENTICATION_FAILED("Logining xato bolib yatibdi"),
    COULD_NOT_UPDATE_RECORD("update atib bolmadi"),
    COULD_NOT_DELETE_RECORD("o'chirib bolmadi"),
    EMAIL_ADDRESS_NOT_VERIFIED("Emailingi verify ataxi");


    private String ErrorMessages;

    ErrorMessage(String errorMessages) {
        ErrorMessages = errorMessages;
    }

    public String getErrorMessages() {
        return ErrorMessages;
    }

    public void setErrorMessages(String errorMessages) {
        ErrorMessages = errorMessages;
    }
}
