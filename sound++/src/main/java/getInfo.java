
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
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
        //List<Track> tracksofAlbum = dao.find(new Track(), album.getTitle());
        JSONObject formDetailsJson = new JSONObject();
        formDetailsJson.put("title", album.getTitle());
        formDetailsJson.put("image", album.getImage());
        JSONArray artists = new JSONArray();
        artists.add(toArrayList(album.getArtists()));
        formDetailsJson.put("numberofsong", album.getNumberofsong());
        formDetailsJson.put("artists", artists);
        formDetailsJson.put("added_date", album.getAddedDate().toString());
        formDetailsJson.put("released_date", album.getReleasedate().toString());
        formDetailsJson.put("cost", album.getCost());
        formDetailsJson.put("label", album.getLabel());
        jsonArray.add(formDetailsJson);
        responseDetailsJson.put("info", jsonArray);
        PrintWriter out = response.getWriter();
        out.write(responseDetailsJson.toJSONString());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private ArrayList<String> toArrayList(List<Artist> list) {
        ArrayList<String> artists = new ArrayList<String>();
        for (Artist a : list) {
            artists.add(a.getName());
        }
        return artists;
    }

}
