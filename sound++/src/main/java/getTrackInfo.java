
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet(name = "getTrackInfo", urlPatterns = {"/getTrackInfo"})
public class getTrackInfo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        JSONObject responseDetailsJson = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        DAO dao = new DAO("songstore");
        int trackRequestID = Integer.parseInt(request.getParameter("id"));
        Track track = dao.read(new Track(), trackRequestID).get(0);
        JSONObject formDetailsJson = new JSONObject();
        formDetailsJson.put("title", track.getTitle());
        formDetailsJson.put("image", track.getAlbum().getImage());
        JSONArray artists = new JSONArray();
        artists.add(toArrayList(track.getAlbum().getArtists()));
        formDetailsJson.put("songwriter", track.getSongwriter());
        formDetailsJson.put("artists", artists);
        formDetailsJson.put("added_date", track.getDate_added().toString());
        formDetailsJson.put("genre", track.getGenre());
        formDetailsJson.put("cost", track.getCost());
        formDetailsJson.put("playlength", track.getPlay_length());
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
