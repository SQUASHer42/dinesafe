package com.example.joshhan.dinesafe;

import android.widget.TextView;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.InputStream;
import java.util.logging.Handler;


public class Parser {
    SAXParserFactory factory;
    SAXParser saxParser;
    Restaurant restaurant;

    public Parser() {
        try {
            factory = SAXParserFactory.newInstance();
            saxParser = factory.newSAXParser();


        } catch (Exception e) {e.printStackTrace();}
    }

    DefaultHandler handler = new DefaultHandler() {
        boolean id = false;
        boolean name = false;
        boolean address = false;
        boolean type = false;
        boolean status = false;
        boolean mi = false;


        public void startElement(String uri, String localName, String qName,
                                 Attributes attributes) throws SAXException {
            if (qName.equalsIgnoreCase("ESTABLISHMENT_NAME")) {
                name = true;
            }
            if (qName.equalsIgnoreCase("ESTABLISHMENT_ADDRESS")) {
                address = true;
            }
            if (qName.equalsIgnoreCase("ESTABLISHMENT_ID")) {
                id = true;
            }
            if (qName.equalsIgnoreCase("ESTABLISHMENTTYPE")) {
                type = true;
            }
            if (qName.equalsIgnoreCase("ESTABLISHMENT_STATUS")) {
                status = true;
            }
            if (qName.equalsIgnoreCase("MINIMUM_INSPECTIONS_PERYEAR")) {
                mi = true;
            }
        }

        public void endElement(String uri, String localName,
                               String qName) throws SAXException {
        }

        public void characters(char ch[], int start, int length) throws SAXException {

            if (name) {
                restaurant.setName(new String(ch, start, length));
                //textView.setText(textView.getText() + "\n\n Name : " + new String(ch, start, length));
                //name = false;
            }
            if (address) {
                restaurant.setAddress(new String(ch, start, length));
                //textView.setText(textView.getText() + "\n\n Address : " + new String(ch, start, length));
                //address = false;
            }
            if (id) {
                restaurant.setId(Integer.parseInt(new String(ch, start, length)));
                //textView.setText(textView.getText() + "\n ID : " + new String(ch, start, length));
                //id = false;
            }
            if (type) {
                restaurant.setType(new String(ch, start, length));
                //textView.setText(textView.getText() + "\n\n Type : " + new String(ch, start, length));
                //type = false;
            }
            if (status) {
                restaurant.setStatus(new String(ch, start, length));
                //textView.setText(textView.getText() + "\n\n Status : " + new String(ch, start, length));
                //status = false;
            }
            if (mi) {
                restaurant.setMinimuminspectionsperyear(Integer.parseInt(new String(ch,start,length)));
                //textView.setText(textView.getText() + "\n\n Minimum Inspections per year : " + new String(ch, start, length));
                //mi = false;
            }
        }

    };

}