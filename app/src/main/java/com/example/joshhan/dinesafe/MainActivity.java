package com.example.joshhan.dinesafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File file = new File("dinesafe.xml");
        Parser parser = new Parser();
        parser.createFile(file);
        NodeList nList = parser.doc.getElementsByTagName("ROW");

        Node example = nList.item(0);
        Element e = (Element) nList;
        Restaurant restaurant = new Restaurant(Integer.parseInt(e.getAttribute("ROW_ID")), e.getAttribute("ESTABLISHMENT_NAME"),
                e.getAttribute("ESTABLISHMENTTYPE"), e.getAttribute("ESTABLISHMENT_ADDRESS"), e.getAttribute("ESTABLISHMENT_STATUS"),
                Integer.parseInt(e.getAttribute("MINIMUM_INSPECTIONS_PERYEAR")));

    }
}
