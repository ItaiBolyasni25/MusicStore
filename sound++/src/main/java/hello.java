
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 1635547
 */
public class hello {
    
    public static void main(String[] args){
        
        System.out.println("hello 1");
        
        System.out.println("hello 2");
        
        
        try {
            URL url = new URL("https://www.cbc.ca/cmlink/rss-arts");
            System.out.println("hello 3");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            System.out.println("hello 4");
            connection.setDoOutput(true);
            System.out.println("hello 5");
            connection.setInstanceFollowRedirects(false);
            System.out.println("hello 6");
            connection.setRequestMethod("POST");
            System.out.println("hello 7");
            connection.setRequestProperty("Content-Type", "application/xml");
            System.out.println("hello 8");

            OutputStream os = connection.getOutputStream();
            System.out.println("hello 9");
            System.out.println(os.toString());
            System.out.println("hello 10");
            os.flush();
            connection.getResponseCode();
            connection.disconnect();
        } catch (Exception e) {
            System.out.println("hello");
            throw new RuntimeException(e);
        }
    } 
}
