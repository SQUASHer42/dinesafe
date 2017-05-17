package com.example.joshhan.dinesafe;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    String xmlContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.xml);
        try {
            xmlContent = getEventsFromAnXML(this);
            textView.setText(xmlContent);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private String getEventsFromAnXML(Activity activity) throws XmlPullParserException, IOException{
        StringBuffer stringBuffer = new StringBuffer();
        XmlResourceParser xpp = activity.getResources().getXml(R.xml.test);
        xpp.next();

        int eventType = xpp.getEventType();
        while(eventType != XmlPullParser.END_DOCUMENT){
            if(eventType == XmlPullParser.START_DOCUMENT){
                stringBuffer.append("----Start XML----");
            }
            else if(eventType == XmlPullParser.START_TAG)
            {
                stringBuffer.append("\nSTART_TAG: "+xpp.getName());
            }
            else if(eventType == XmlPullParser.END_TAG)
            {
                stringBuffer.append("\nEND_TAG: "+xpp.getName());
            }
            else if(eventType == XmlPullParser.TEXT)
            {
                stringBuffer.append("\nTEXT: "+xpp.getText());
            }
            eventType = xpp.next();
        }
        stringBuffer.append("\n--- End XML ---");
        return stringBuffer.toString();
    }
}

