package com.ss.zemluk.web.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.ss.zemluk.web.jsonview.Views;

import java.io.Serializable;

public class Data implements Serializable {

    @JsonView(Views.Public.class)
    String stringValue;

    public Data() {
    }

    public Data(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return "Data [stringValue=" + stringValue + "]";
    }

}
