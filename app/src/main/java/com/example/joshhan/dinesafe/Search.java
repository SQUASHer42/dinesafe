package com.example.joshhan.dinesafe;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

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
            Log.i(Search.class.getSimpleName(), query);
            new CloudantHandler(new AsyncResponse() {
                @Override
                public void processFinish(List<Review> output) {
                    //Log.i(Search.class.getSimpleName(), "hey");
                    LinearLayout ll = (LinearLayout)findViewById(R.id.results);
                    ArrayList<View> results = new ArrayList<View>();

                    if(output!=null){
                        for(int i = 0; i < output.size();i++){
                            final Review review = output.get(i);
                            Log.d(Search.class.getSimpleName(), review.toString());
                            //message=message+review.toString();
                            final TextView v = new TextView(Search.this);
                            v.setText(review.forSearch());
                            v.setTextColor(Color.BLACK);
                            v.setBackgroundResource(R.drawable.back);
                            v.setClickable(true);
                            View.OnClickListener l = new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(Search.this, MainActivity.class);
                                    //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent.putExtra("address", review.getAddress());
                                    intent.putExtra("name", review.getName());
                                    intent.putExtra("latitude", review.getLatitude());
                                    intent.putExtra("longitude", review.getLongitude());
                                    intent.putExtra("comments", review.getComments());
                                    intent.putExtra("status", review.getStatus());
                                    intent.putExtra("severity", review.getSeverity());
                                    startActivity(intent);
                                }
                            };
                            v.setOnClickListener(l);
                            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT
                            );
                            lp.setMargins(50, 15, 50, 15);
                            v.setLayoutParams(lp);
                            results.add(v);
                            ll.addView(results.get(i));
                        }
                    }
                    else{
                        TextView v = new TextView(Search.this);
                        v.setText("Query not found.");
                        v.setTextColor(Color.BLACK);
                        results.add(v);
                        ll.addView(results.get(0));
                    }
                }
            }).execute(query);
        }

    }
}
