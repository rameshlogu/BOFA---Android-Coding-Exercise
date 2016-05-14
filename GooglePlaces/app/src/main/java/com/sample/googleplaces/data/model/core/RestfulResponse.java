package com.sample.googleplaces.data.model.core;

import android.util.Log;

import com.sample.googleplaces.util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This is a generic response model. The high level parsing will be
 * happening here.
 *
 * Created by rameshloganathan on 14/05/16.
 */
public abstract class RestfulResponse {
    // Constants
    private String status;
    private RestfulError error;

    /**
     * Constructor
     *
     * @param response
     *         the string response to parse
     */
    public RestfulResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            parseStatus(jsonObject);
            if (isSuccess()) {
                parseData(jsonObject);
            } else {
                parseError(jsonObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            setError(getDefaultError());
        }
    }

    /**
     * @return the statusSuccess
     */
    public static String getStatusSuccess() {
        return Constants.STATUS_SUCCESS;
    }

    /**
     * Parse the data. Children of this class will implement this and parse the data according to
     * the business logic.
     *
     * @param response
     */
    public abstract void parseData(JSONObject response);

    /**
     * Get the default error from children. If anything goes wrong in the service response, this
     * will help to define the default error.
     *
     * @return the defaualt error
     */
    public abstract RestfulError getDefaultError();

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *         the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the error
     */
    public RestfulError getError() {
        if (error == null) {
            // If the error object is null, create the default error data.
            error = new RestfulError(Constants.GENERIC_SERVICE_ERROR);
        }
        return error;
    }

    /**
     * @param error
     *         the error to set
     */
    public void setError(RestfulError error) {
        this.error = error;
    }

    /** Helpers/Parsers Start **/

    /**
     * Parse the status from the response and set it
     *
     * @param response
     *         the JSONObject which has been retrieved from service
     */
    protected void parseStatus(JSONObject response) {
        try {
            setStatus(response.getString(Constants.STATUS));
        } catch (JSONException exception) {
            // Set the status as empty in case of any runtime exception. This
            // will avoid the crash.
            setStatus("");
            Log.e(RestfulResponse.class.getName(), exception.getMessage());
        }
    }

    /**
     * Parse the error
     *
     * @param response
     *         the JSONObject which has been retrieved from service
     */
    private void parseError(JSONObject response) {
        setError(new RestfulError(response));
    }

    public boolean isSuccess() {
        return Constants.STATUS_SUCCESS.equalsIgnoreCase(status);
    }

    /**
     * Parse the string from json object
     *
     * @param jsonObj
     *         the JSON object retrieved from service
     * @param key
     *         the key to parse
     * @return the string value
     */
    protected String getString(JSONObject jsonObj, String key) {
        String retVal = "";
        try {
            retVal = jsonObj.getString(key);
        } catch (JSONException exception) {
            Log.e(RestfulResponse.class.getName(), exception.getMessage());

        }
        return retVal;
    }

    /**
     * Parse the integer from json object
     *
     * @param jsonObj
     *         the JSON object retrieved from service
     * @param key
     *         the key to parse
     * @return the integer value
     */
    protected int getInt(JSONObject jsonObj, String key) {
        int retVal = 0;
        try {
            retVal = jsonObj.getInt(key);
        } catch (JSONException exception) {
            Log.e(RestfulResponse.class.getName(), exception.getMessage());

        }
        return retVal;
    }

    /**
     * Parse the boolean from json object
     *
     * @param jsonObj
     *         the JSON object retrieved from service
     * @param key
     *         the key to parse
     * @return the boolean value
     */
    protected boolean getBoolean(JSONObject jsonObj, String key) {
        boolean retVal = false;
        try {
            retVal = jsonObj.getBoolean(key);
        } catch (JSONException exception) {
            Log.e(RestfulResponse.class.getName(), exception.getMessage());

        }
        return retVal;
    }

    /**
     * Parse an array from json object
     *
     * @param jsonObj
     *         the JSON object retrieved from service
     * @param key
     *         the key to parse
     * @return the array
     */
    protected JSONArray getJSONArray(JSONObject jsonObj, String key) {
        JSONArray retVal = new JSONArray();
        try {
            retVal = jsonObj.getJSONArray(key);
        } catch (JSONException exception) {
            Log.e(RestfulResponse.class.getName(), exception.getMessage());

        }
        return retVal;
    }

    /**
     * Parse an json object from json object
     *
     * @param jsonObj
     *         the JSON object retrieved from service
     * @param key
     *         the key to parse
     * @return the array
     */
    protected JSONObject getJSONObject(JSONObject jsonObj, String key) {
        JSONObject retVal = new JSONObject();
        try {
            retVal = jsonObj.getJSONObject(key);
        } catch (JSONException exception) {
            Log.e(RestfulResponse.class.getName(), exception.getMessage());

        }
        return retVal;
    }
    /** Helpers/Parsers End **/
}