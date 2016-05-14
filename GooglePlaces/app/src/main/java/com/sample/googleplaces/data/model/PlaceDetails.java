package com.sample.googleplaces.data.model;

import com.sample.googleplaces.data.model.core.RestfulError;
import com.sample.googleplaces.data.model.core.RestfulResponse;
import com.sample.googleplaces.util.Constants;

import org.json.JSONObject;

/**
 * This class is a model for Place details which will be retrieved from Google place details API
 * <p/>
 * Created by rameshloganathan on 14/05/16.
 */
public class PlaceDetails extends RestfulResponse {

    private String id;
    private String formattedAddress;
    private String iconUrl;
    private String phone;

    /**
     * Constructor
     *
     * @param response
     *         the JSON response to parse
     */
    public PlaceDetails(String response) {
        super(response);
    }

    /**
     * Parse the data.
     *
     * @param response
     *         the Json Response object to parse
     */
    @Override
    public void parseData(JSONObject response) {
        JSONObject result = getJSONObject(response, Constants.KEY_RESULT);
        setId(getString(result, Constants.KEY_FORMATTED_ADDRESS));
        setFormattedAddress(getString(result, Constants.KEY_FORMATTED_ADDRESS));
        setPhone(getString(result, Constants.KEY_PHONE));
        setIconUrl(getString(result, Constants.KEY_ICON));
    }

    /**
     * Get the default error. If anything goes wrong in the service response, this
     * will help to define the default error.
     *
     * @return the defaualt error
     */
    @Override
    public RestfulError getDefaultError() {
        return new RestfulError(Constants.DETAILS_SERVICE_ERROR);
    }


    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
