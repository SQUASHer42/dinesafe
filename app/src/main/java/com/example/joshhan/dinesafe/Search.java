package com.example.joshhan.dinesafe;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//import java.io.InputStream;

public class Search extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent){
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
            System.out.println(query);
        }
        //getRestaurant();
    }

    /*private void getRestaurant(){
        try{
            InputStream is = getResources().openRawResource(R.raw.test);
            Parser parser = new Parser();
            parser.saxParser.parse(is, parser.handler);

            System.out.println("Restaurant" + parser.restaurant.toString());
            //textView.setText(parser.restaurant.toString());
        }
        catch(Exception e){e.printStackTrace();}
    }*/
}
