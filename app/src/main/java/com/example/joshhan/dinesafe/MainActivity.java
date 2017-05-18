package com.example.joshhan.dinesafe;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    TextView textView;
    String xmlContent;


    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        LatLng sydney = new LatLng(-33.852, 151.211);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        textView = (TextView) findViewById(R.id.xml);
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();

            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                boolean id = false;
                boolean name = false;
                boolean address = false;
                boolean type = false;
                boolean status = false;
                boolean mi = false;


                public void startElement(String uri, String localName,String qName,
                                         Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("ESTABLISHMENT_NAME"))
                    {
                        name = true;
                    }
                    if (qName.equalsIgnoreCase("ESTABLISHMENT_ADDRESS"))
                    {
                        address = true;
                    }
                    if (qName.equalsIgnoreCase("ESTABLISHMENT_ID"))
                    {
                        id = true;
                    }
                    if (qName.equalsIgnoreCase("ESTABLISHMENTTYPE"))
                    {
                        type = true;
                    }
                    if (qName.equalsIgnoreCase("ESTABLISHMENT_STATUS"))
                    {
                        status = true;
                    }
                    if (qName.equalsIgnoreCase("MINIMUM_INSPECTIONS_PERYEAR"))
                    {
                        mi = true;
                    }
                }

                public void endElement(String uri, String localName,
                                       String qName) throws SAXException {
                }

                public void characters(char ch[], int start, int length) throws SAXException {
                    StringBuffer sb = new StringBuffer();
                    if (name) {
                        textView.setText(textView.getText()+"\n\n Name : " + new String(ch, start, length));
                        name = false;
                    }
                    if (address) {
                        textView.setText(textView.getText()+"\n\n Address : " + new String(ch, start, length));
                        address = false;
                    }
                    if (id) {
                        textView.setText(textView.getText()+"\n ID : " + new String(ch, start, length));
                        id = false;
                    }
                    if (type) {
                        textView.setText(textView.getText()+"\n\n Type : " + new String(ch, start, length));
                        type = false;
                    }
                    if (status) {
                        textView.setText(textView.getText()+"\n\n Status : " + new String(ch, start, length));
                        status = false;
                    }
                    if (mi) {
                        textView.setText(textView.getText()+"\n\n Minimum Inspections per year : " + new String(ch, start, length));
                        mi = false;
                    }
                }

            };

            InputStream is = getResources().openRawResource(R.raw.test);
            saxParser.parse(is, handler);

        } catch (Exception e) {e.printStackTrace();}
    }





    /*private String getEventsFromAnXML(Activity activity) throws XmlPullParserException, IOException{
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
    }*/
}

