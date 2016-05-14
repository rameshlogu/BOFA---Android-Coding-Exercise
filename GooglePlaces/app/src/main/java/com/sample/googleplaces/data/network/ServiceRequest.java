package com.sample.googleplaces.data.network;

import com.sample.googleplaces.util.Constants;

import java.util.Hashtable;

/**
 * Service request parameters
 *
 * Created by rameshloganathan on 13/05/16.
 */
public class ServiceRequest {
    private String id;
    private String url;
    private Hashtable<String,String> parameters;
    private String requestMethod;
    //default timeout for a request. Some service requests might have a different timeout for
    // http calls
    private int requestTimeout = 60 * 1000; // 1 min - 60000 milli seconds

    /**
     * @return
     */
    public Hashtable<String,String> getParameters() {
        return parameters;
    }

    /**
     * @param parameters
     */
    public void setParameters(Hashtable<String,String> parameters) {
        this.parameters = parameters;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *         the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     *         the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return
     */
    public String getRequestMethod() {
        return (requestMethod != null) ? requestMethod : Constants.REQUEST_GET;
    }

    /**
     * @param requestMethod
     */
    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    /**
     * @return
     */
    public int getRequestTimeout() {
        return requestTimeout;
    }

    /**
     * @param requestTimeout
     */
    public void setRequestTimeout(int requestTimeout) {
        this.requestTimeout = requestTimeout;
    }
}