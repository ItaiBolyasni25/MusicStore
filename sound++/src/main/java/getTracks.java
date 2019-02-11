
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author maian
 */
@WebServlet(name = "getTracks", urlPatterns = {"/getTracks"})
public class getTracks extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int currentPage;
        int offset;
        int itemPerPage = 15;
        DAO dao = new DAO("songstore");
        int totalPages;
        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
            offset = (currentPage - 1) * itemPerPage;
        } else {
            currentPage = 1;
            offset = 0;
        }
        int totalRows = dao.findAll(new Track()).size();
        if (totalRows > itemPerPage) {
            totalPages = (int) Math.ceil((totalRows * 1.0) / itemPerPage);
        } else {
            totalPages = 1;
        }
        List<Track> list = dao.findWithLimit(new Track(), offset, itemPerPage);
        Internationalization internationalization = new Internationalization();
        request.setAttribute("internationalization", internationalization);
        request.setAttribute("trackList", list);
        request.setAttribute("totalPage", totalPages);
        request.setAttribute("currentPage", currentPage);
        RequestDispatcher view = request.getRequestDispatcher("Tracks.jsp");
        view.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
