package com.sample.googleplaces.data.network;

import android.net.Uri;
import android.util.Log;

import com.sample.googleplaces.util.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Hashtable;

import javax.net.ssl.HttpsURLConnection;

/**
 * This class will manage all services for the application. This class will take care of the
 * default headers like timeout and cookies.
 * <p/>
 * Created by rameshloganathan on 13/05/16.
 */
public class ServiceManager {

    private static ServiceManager _instance;

    /**
     * Get the Singleton instance
     *
     * @return
     */
    public static ServiceManager getInstance() {
        if (_instance == null) {
            _instance = new ServiceManager();
        }
        return _instance;
    }

    /**
     * Gets a string from the provided {@link InputStream}
     *
     * @param is
     *         The {@link InputStream} that should contain a string
     * @return The String that was extracted from the {@link InputStream}
     */
    private String convertStreamToString(InputStream is) {
        /*
         * To convert the InputStream to String we use the
       * BufferedReader.readLine() method. We iterate until the BufferedReader
       * return null which means there's no more data to read. Each line will
       * appended to a StringBuilder and returned as String.
       */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * Connect the remote service and read the stream as string.
     *
     * @param request
     *         the request parameters
     * @return the response object
     */
    public ServiceResponse connectServiceAndGetResponse(
            ServiceRequest request) {
        InputStream instream = null;
        HttpURLConnection conn = null;
        ServiceResponse serviceResponse = new ServiceResponse();

        try {
            conn = getUrlConnectionObject(request);

            // send request, get response stream
            instream = conn.getInputStream();
            serviceResponse.setStatusCode(conn.getResponseCode());

            switch (conn.getResponseCode()) {
                case HttpsURLConnection.HTTP_OK:
                    String responseStr = convertStreamToString(instream);
                    serviceResponse.setStringResponse(responseStr);
                    break;
                //Add more cases in future
                default:
                    break;
            }
        } catch (Exception exception) {
            Log.e(ServiceManager.class.getName(), exception.getMessage());
            if (exception instanceof SocketTimeoutException) {
                serviceResponse.setStatusCode(Constants.STATUS_TIME_OUT_EXCEPTION);
            } else {
                serviceResponse.setStatusCode(Constants.STATUS_CODE_EXCEPTION);
            }
        } finally {
            try {
                if (instream != null) instream.close();
                if (conn != null) conn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return serviceResponse;
    }

    /**
     * Get a http connection object with all default properties required for a service call.
     *
     * @param request
     *         the service request object
     * @return instance of URLConnection with appropriate properties
     */
    private HttpURLConnection getUrlConnectionObject(ServiceRequest request) throws IOException {
        HttpURLConnection conn;
        URL requestUrl = null;
        // define request url
        requestUrl = new URL(buildRequestUrl(request));

        /* create and configure http request connection object */
        conn = (HttpURLConnection) requestUrl.openConnection();
        conn.setRequestMethod(request.getRequestMethod());
        conn.setRequestProperty(Constants.USER_AGENT, System.getProperty("http.agent"));
        conn.setConnectTimeout(request.getRequestTimeout());
        conn.setReadTimeout(request.getRequestTimeout());
        conn.setInstanceFollowRedirects(false);
        return conn;
    }

    /**
     * Generate request URL and append the request params if there are any specific to the
     * functionality
     *
     * @param sRequest
     * @return url string
     * @throws UnsupportedEncodingException
     */
    private String buildRequestUrl(ServiceRequest sRequest) throws UnsupportedEncodingException {

        // define request url
        Uri.Builder builder = Uri.parse(sRequest.getUrl()).buildUpon();

        // append the parameters if there are any
        Hashtable<String, String> params = sRequest.getParameters();
        if (sRequest.getParameters() != null) {
            for (String key : params.keySet()) {
                builder.appendQueryParameter(key, params.get(key));
            }
        }

        //build the uri
        Uri builderUri = builder.build();
        return builderUri.toString();
    }
}