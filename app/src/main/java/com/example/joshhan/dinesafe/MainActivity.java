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

        File file = new File(getResources().getValue(R.values.test.xml));
        System.out.println(file.exists());
        /*Parser parser = new Parser();
        parser.createFile(file);
        parser.doc.getDocumentElement().normalize();
        NodeList nList = parser.doc.getElementsByTagName("ROW");

        Node example = nList.item(0);
        Element e = (Element) nList;
        /*Restaurant restaurant = new Restaurant(
                Integer.parseInt(e.getElementsByTagName("ROW_ID").item(0).getTextContent()),
                e.getElementsByTagName("ESTABLISHMENT_NAME").item(0).getTextContent(),
                e.getElementsByTagName("ESTABLISHMENTTYPE").item(0).getTextContent(),
                e.getElementsByTagName("ESTABLISHMENT_ADDRESS").item(0).getTextContent(),
                e.getElementsByTagName("ESTABLISHMENT_STATUS").item(0).getTextContent(),
                Integer.parseInt(e.getElementsByTagName("MINIMUM_INSPECTIONS_PERYEAR").item(0).getTextContent()));
        System.out.println(restaurant.getName());
        */
    }
}
