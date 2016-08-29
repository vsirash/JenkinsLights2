package org.vsirash.lights.utils;

import org.vsirash.lights.model.BuildStatus;
import org.vsirash.lights.model.Status;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.servlet.http.HttpServletRequest;
import javax.xml.xpath.XPathExpression;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Date;

/**
 * Created by Volodymyr Sirash on 8/15/16.
 */
public class StatusParserImpl implements StatusParser {

    static XPathExpression statusExpr;
    static XPathExpression buildNoExpr;
    static XPathExpression jobNameExpr;

    static {
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        try {
            statusExpr = xpath.compile("//build/status/text()");
            buildNoExpr = xpath.compile("//build/number/text()");
            jobNameExpr = xpath.compile("//name/text()");
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

    }


    @Override
    public Status parseStatus(HttpServletRequest request) throws Exception {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();

       String line = null;
        while((line = reader.readLine())!=null){
            sb.append(line);
        }

        InputSource is = new InputSource(new StringReader(sb.toString()));
        Document doc = builder.parse(is);


        String statusStr = statusExpr.evaluate(doc);
        String buildNo = buildNoExpr.evaluate(doc);
        String jobName = jobNameExpr.evaluate(doc);

        Status status = new Status(request.getRemoteAddr(), BuildStatus.valueOf(statusStr), buildNo, jobName, new Date(),sb.toString());
        return status;
    }
}
