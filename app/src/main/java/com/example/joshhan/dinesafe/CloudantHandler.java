package com.example.joshhan.dinesafe;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;

import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by panda on 2017-05-31.
 */

public class CloudantHandler extends AsyncTask<String, Void, Review>{
    private Exception e;
    private final String user = "alainlou";
    private final String password = "alainlou";
    private static final String TAG = CloudantHandler.class.getSimpleName();
    //private Review r;

    protected Review doInBackground(String... queries) {
        try {
            CloudantClient cloudantClient = ClientBuilder.account(user).username(user).password(password).build();
            Database db = cloudantClient.database("dinesafe", false);
            List<Review> r = db.findByIndex("_id:"+queries, Review.class);
            return r.get(0);
        }
        catch(Exception e) {
            e.printStackTrace();
            //Log.i(TAG, "It didn't work.");
            Review r = new Review(1,2,"a", "a", "a", "a,", 2, "a", "a", "a", "a", "a");
            return r;
        }
    }

    protected void onPostExecute(Review r) {
        //Log.i(TAG, r.toString());
        Log.i(TAG, r.toString());
    }
}
