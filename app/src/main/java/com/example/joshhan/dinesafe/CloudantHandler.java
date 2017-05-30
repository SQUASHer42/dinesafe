package com.example.joshhan.dinesafe;

import android.util.Log;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;

/**
 * Created by panda on 2017-05-29.
 */

public class CloudantHandler {
    CloudantClient cloudantClient;
    Database db;
    String TAG = CloudantHandler.class.getSimpleName();

    public CloudantHandler(CloudantClient c, String name){
        cloudantClient = c;
        db = c.database(name, false);
    }

    public void addEntry(Restaurant r){
        db.save(r);
        Log.i(TAG, "Entry has been stored");
    }

}
