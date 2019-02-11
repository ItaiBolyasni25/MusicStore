
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author maian
 */
@WebServlet(name = "getInfo", urlPatterns = {"/getInfo"})
public class getInfo extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        JSONObject responseDetailsJson = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        DAO dao = new DAO("songstore");
        int albumRequestID = Integer.parseInt(request.getParameter("id"));
        Album album = dao.read(new Album(), albumRequestID).get(0);
        JSONObject formDetailsJson = new JSONObject();
        formDetailsJson.put("title", album.getTitle());
        formDetailsJson.put("image", album.getImage());
        formDetailsJson.put("artists", album.getArtists());
        formDetailsJson.put("released_date", album.getReleaseDate());
        formDetailsJson.put("cost", album.getCost());
        formDetailsJson.put("label", album.getLabel());
        jsonArray.add(formDetailsJson);
        responseDetailsJson.put("object", jsonArray);
        PrintWriter out = response.getWriter(); 
        out.write(responseDetailsJson);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
