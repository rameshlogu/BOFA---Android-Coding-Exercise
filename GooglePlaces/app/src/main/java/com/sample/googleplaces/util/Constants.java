package com.sample.googleplaces.util;

/**
 * Application Constants
 *
 * Created by rameshloganathan on 13/05/16.
 */
public class Constants {

    //Service
    public static final int TIME_OUT = 60 * 1000;
    public static final int STATUS_CODE_EXCEPTION = 1000;
    public static final int STATUS_TIME_OUT_EXCEPTION = 1100;
    public static final String REQUEST_GET = "GET";
    public static final String REQUEST_POST = "POST";
    public static final String USER_AGENT = "User-Agent";
    public static final String CACHE_CONTROL = "Cache-Control";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CHAR_SET = "charset";

    //Generic Model
    public static final String STATUS = "status";
    public static final String STATUS_SUCCESS = "ok";
    public static final String ERROR_MESSAGE = "error_message";

    //PlaceSuggestions Model
    public static final String KEY_PREDICTIONS = "predictions";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_ID = "place_id";
    public static final String SUGGESTION_SERVICE_ERROR = "Unable to get suggestions";

    //PlaceDetails Model
    public static final String KEY_RESULT = "result";
    public static final String KEY_FORMATTED_ADDRESS = "formatted_address";
    public static final String KEY_PHONE = "formatted_phone_number";
    public static final String KEY_ICON = "icon";
    public static final String DETAILS_SERVICE_ERROR = "Unable to get details";

    //Generic Errors
    public static final String GENERIC_SERVICE_ERROR = "Unable to complete the request";

    //Suggestion Provider
    public static final String SUGGESTION_SERVICE_ID = "suggestion_service_id";
    public static final String PARAM_API_KEY = "key";
    public static final String PARAM_SENSOR_KEY = "sensor";
    public static final String PARAM_COMPONENTS_KEY = "components";
    public static final String PARAM_INPUT_KEY = "input";
    public static final String PARAM_API_VALUE = "AIzaSyBCwIqFJbN-_2GFnbuGVMpHblTZPmXMeoo";
    public static final String PARAM_COMPONENTS_VALUE = "country:us";

    //Detail Loader
    public static final String DETAILS_SERVICE_ID = "details_service_id";
    public static final String PARAM_PLACEID_KEY = "placeid";
}
