package com.kanu.interviews.model;

public class ResponseMessage {

    public static final ResponseMessage ADD_SUCCESS = new ResponseMessage("account successfully added");

    public static final ResponseMessage DELETE_SUCCESS = new ResponseMessage("account successfully deleted");

    private ResponseMessage(String responseMessage){
        this.message = responseMessage;
    }

    private String message;
}
