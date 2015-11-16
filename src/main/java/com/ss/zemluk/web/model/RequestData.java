package com.ss.zemluk.web.model;

/**
 * Class for getting data object in request
 */
public class RequestData {

    private String stringValue;
    private int position;

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "RequestData [stringValue=" + stringValue + "]";
    }

}
