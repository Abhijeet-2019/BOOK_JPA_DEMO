package com.abhijeet.books.libraryApp.controllers;

import java.io.Serializable;

public class ResponseObject implements Serializable{

    private String responseStatus;
    private Object responseData;


    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public Object getResponseData() {
        return responseData;
    }

    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }
}
