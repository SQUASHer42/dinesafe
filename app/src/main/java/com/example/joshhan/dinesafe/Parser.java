package com.example.joshhan.dinesafe;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class Parser {
    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    Document doc;

    public Parser() {
        factory = DocumentBuilderFactory.newInstance();
        builder = facotry.newDocumentBuilder();

    }

    public void createFile() {
        StringBuilder xmlStringBuilder = new StringBuilder();
        xmlStringBuilder.append("<?xml version="1.0"?> <class> </class>");
        ByteArrayInputStream input =  new ByteArrayInputStream(
                xmlStringBuilder.toString().getBytes("UTF-8"));
        doc = builder.parse(input);
    }
}