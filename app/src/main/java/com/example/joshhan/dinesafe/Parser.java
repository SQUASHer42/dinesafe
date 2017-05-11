package com.example.joshhan.dinesafe;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;

import java.io.File;


public class Parser {
    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    Document doc;

    public Parser() {
        try{
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }

    public void createFile(File file) {
        try{
            doc = builder.parse(file);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}