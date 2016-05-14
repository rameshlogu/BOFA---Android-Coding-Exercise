package com.sample.googleplaces.data.model.core;

import android.util.Log;

import com.sample.googleplaces.util.Constants;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This is a generic error model
 *
 * Created by rameshloganathan on 14/05/16.
 */
public class RestfulError {
    // Constants
    private String message;

    /**
     * Parse the error attributes in the constructor
     *
     * @param errorObj
     *         the error response from server
     */
    public RestfulError(JSONObject errorObj) {
        try {
            if (errorObj != null) {
                setMessage(errorObj.getString(Constants.ERROR_MESSAGE));
            } else {
                // If the object is null, set the default error data
                setMessage(Constants.GENERIC_SERVICE_ERROR);
            }

        } catch (JSONException exception) {
            Log.e(RestfulError.class.getName(), exception.getMessage());
        }
    }

    /**
     * Constructor
     *
     * @param errorMessage
     *         the default error message
     */
    public RestfulError(String errorMessage) {
        setMessage(errorMessage);
    }

    /**
     * @return the message
     */
    public String getMessage() {
        if (message == null) {
            message = Constants.GENERIC_SERVICE_ERROR;
        }
        return message;
    }

    /**
     * @param message
     *         the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}