/**
 *
 */
package com.sample.googleplaces.data.network;

/**
 * Response model for a service
 *
 * Created by rameshloganathan on 13/05/16.
 */
public class ServiceResponse {
    private int statusCode;
    private String stringResponse;

    /**
     * @return the statusCode
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return the stringResponse
     */
    public String getStringResponse() {
        if (stringResponse == null) {
            stringResponse = "";
        }
        return stringResponse;
    }

    /**
     * @param stringResponse the stringResponse to set
     */
    public void setStringResponse(String stringResponse) {
        this.stringResponse = stringResponse;
    }
}