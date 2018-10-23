package com.kanu.interviews.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ResponseMessage {

    @JsonIgnore
    public static final ResponseMessage ADD_SUCCESS = new ResponseMessage("account successfully added");

    @JsonIgnore
    public static final ResponseMessage DELETE_SUCCESS = new ResponseMessage("account successfully deleted");

    private ResponseMessage(String responseMessage){
        this.message = responseMessage;
    }

    @JsonProperty("message")
    private String message;
}
