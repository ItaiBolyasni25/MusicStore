/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author 1635547
 */
@Named(value = "newsFeeds")
@SessionScoped
public class NewsFeeds implements Serializable {

    /**
     * Creates a new instance of NewsFeeds
     */
    public NewsFeeds() {
    }
    
    private StringBuffer sendPost() {

        StringBuffer response = new StringBuffer();
        String url = "https://example.com/snowbound/AjaxServlet";
        final String CONTENT_LENGTH = "131";
        final String CONTENT_TYPE = "application/x-www-form-urlencoded";
        final String ACCEPT_LANGUAGE = "en-US,en;q=0.8";

        try {
            //create http connection
            URL obj = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) obj.openConnection();

            //add request header
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestProperty("Accept-Language", ACCEPT_LANGUAGE);
            connection.setRequestProperty("Content-Type", CONTENT_TYPE);
            connection.setRequestProperty("Content-Length", CONTENT_LENGTH);

            DataOutputStream output = new DataOutputStream(connection.getOutputStream());

            //form data
            String content = "documentId=3896&action=getAnnotationModel&annotationLayer=1&pageCount=1&pageIndex=0";

            //write output stream and close output stream
            output.writeBytes(content);
            output.flush();
            output.close();

            //read in the response data
            BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while((inputLine = input.readLine()) != null) {
                response.append(inputLine.toString());
            }

            //close input stream
            input.close();

            //print out content
            int responseCode = connection.getResponseCode();
            System.out.println("response code: " + responseCode);
            System.out.println("respone is: " + response);

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
