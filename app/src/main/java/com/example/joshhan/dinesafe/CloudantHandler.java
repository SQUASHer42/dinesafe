package com.example.joshhan.dinesafe;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.org.lightcouch.NoDocumentException;

import java.lang.reflect.Array;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by panda on 2017-05-31.
 */

public class CloudantHandler extends AsyncTask<String, Void, List<Restaurant>>{
    private Exception e;
    private final String user = "alainlou";
    private final String password = "alainlou";
    private static final String TAG = CloudantHandler.class.getSimpleName();
    public AsyncResponse delegate = null;

    public CloudantHandler(AsyncResponse delegate){
        this.delegate = delegate;
    }

    protected List<Restaurant> doInBackground(String... queries) {
        Log.i(TAG, queries[0]);
        try {
            CloudantClient cloudantClient = ClientBuilder.account(user).username(user).password(password).build();
            Database db = cloudantClient.database("final", false);

            String selector = String.format("\"selector\": { \"name\": {\"$eq\": \"%s\"}}", queries[0].toUpperCase());
            Log.i(TAG, selector);

            List<Restaurant> r = db.findByIndex(selector, Restaurant.class);
            return r;
        }
        catch(NoDocumentException nde) {
            Log.e(TAG, nde.toString());
            //Log.d(TAG, "It didn't work.");
            return null;
        }
        catch(Exception e){
            Log.e(TAG, e.toString());
            Log.d(TAG, "nooooo");
            return null;
        }

    }

    protected void onPostExecute(List<Restaurant> r) {
        //Log.i(TAG, r.toString()+"");
        delegate.processFinish(r);
    }
}
