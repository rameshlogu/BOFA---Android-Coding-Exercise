package com.sample.googleplaces.data.model;

import com.sample.googleplaces.data.model.core.RestfulError;
import com.sample.googleplaces.data.model.core.RestfulResponse;
import com.sample.googleplaces.util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * This class is a model for Suggestion list which will be retrieved from Google places auto
 * complete API
 * <p/>
 * Created by rameshloganathan on 14/05/16.
 */
public class PlaceSuggestions extends RestfulResponse {

    private ArrayList<Suggestion> suggestions;

    public ArrayList<Suggestion> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(ArrayList<Suggestion> suggestions) {
        this.suggestions = suggestions;
    }

    /**
     * Constructor
     *
     * @param response
     *         the JSON response to parse
     */
    public PlaceSuggestions(String response) {
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
        ArrayList<Suggestion> suggestions = new ArrayList<Suggestion>();
        JSONArray jsonArray = getJSONArray(response, Constants.KEY_PREDICTIONS);
        try {
            //Iterate through all suggestions
            for (int i = 0; i < jsonArray.length(); i++) {
                Suggestion suggestion = new Suggestion(jsonArray.getJSONObject(i));
                suggestions.add(suggestion);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        setSuggestions(suggestions);
    }

    /**
     * Get the default error. If anything goes wrong in the service response, this
     * will help to define the default error.
     *
     * @return the defaualt error
     */
    @Override
    public RestfulError getDefaultError() {
        return new RestfulError(Constants.SUGGESTION_SERVICE_ERROR);
    }

    /**
     * Suggestion model with appropriate properties
     */
    public class Suggestion {
        private String id;
        private String description;

        /**
         * Constructor
         *
         * @param jsonObject
         *         retrieved Json object to parse required properties
         */
        public Suggestion(JSONObject jsonObject) {
            setId(getString(jsonObject, Constants.KEY_ID));
            setDescription(getString(jsonObject, Constants.KEY_DESCRIPTION));
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
