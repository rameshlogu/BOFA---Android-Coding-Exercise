package com.sample.googleplaces.data.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.sample.googleplaces.data.model.PlaceDetails;
import com.sample.googleplaces.data.network.ServiceManager;
import com.sample.googleplaces.data.network.ServiceRequest;
import com.sample.googleplaces.data.network.ServiceResponse;
import com.sample.googleplaces.data.provider.SuggestionProvider;
import com.sample.googleplaces.util.Constants;
import com.sample.googleplaces.util.URLStore;

import java.net.HttpURLConnection;
import java.util.Hashtable;

/**
 * This is loader to load place details from Google Places service
 *
 * Created by rameshloganathan on 14/05/16.
 */
public class PlaceDetailsLoader extends AsyncTaskLoader<PlaceDetails> {
    private PlaceDetails mResult;
    private String mPlaceId;

    /**
     * Constructor
     *
     * @param context
     *         the context of the application/activity.
     * @param placeId
     *         the place id to get details
     */
    public PlaceDetailsLoader(Context context, String placeId) {
        super(context);
        mPlaceId = placeId;
    }

    /**
     * Load the service in background and get the details
     *
     * @see android.content.AsyncTaskLoader#loadInBackground()
     */
    @Override
    public PlaceDetails loadInBackground() {
        //Connect Service
        ServiceRequest request = new ServiceRequest();
        request.setUrl(URLStore.URL_GOOGLE_PLACES_DETAIL);
        request.setId(Constants.DETAILS_SERVICE_ID);
        request.setParameters(getParameters());
        ServiceResponse response = ServiceManager.getInstance()
                .connectServiceAndGetResponse(request);

        //Parse response
        PlaceDetails details = null;
        if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
            Log.d(SuggestionProvider.class.getName(), response.getStringResponse());
            details = new PlaceDetails(response.getStringResponse());
        }

        return details;
    }

    /**
     * build request parameters
     *
     * @return the Hashtable of parameters
     */
    private Hashtable<String, String> getParameters() {
        // get request params
        Hashtable<String, String> parameters = new Hashtable<String, String>();
        parameters.put(Constants.PARAM_API_KEY, Constants.PARAM_API_VALUE);
        parameters.put(Constants.PARAM_PLACEID_KEY, mPlaceId);
        return parameters;
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged() || mResult == null) {
            forceLoad();
        } else {
            deliverResult(mResult);
        }
    }

    @Override
    protected void onReset() {
        super.onReset();
        mResult = null;
    }

    @Override
    public void deliverResult(PlaceDetails data) {
        if (isReset()) {
            return;
        }
        mResult = data;
        super.deliverResult(data);
    }
}
