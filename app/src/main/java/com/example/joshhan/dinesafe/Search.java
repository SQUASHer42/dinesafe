package com.example.joshhan.dinesafe;

import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

//import java.io.InputStream;

public class Search extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent){
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
            //Review r = (Review) new CloudantHandler().execute(query);


            LinearLayout ll = (LinearLayout)findViewById(R.id.results);
            TextView v = new TextView(this.getApplicationContext());
            ArrayList<View> results = new ArrayList<View>();
            v.setText("a;sdlkfja;sldkfja;sdfkj");
            v.setTextColor(Color.BLACK);
            results.add(v);
            ll.addView(results.get(0));
        }

    }

}
