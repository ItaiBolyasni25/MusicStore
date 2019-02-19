
import com.mycompany.Model.Album;
import com.mycompany.Persistence.DAO;
import com.mycompany.Utilities.Internationalization;
import java.io.IOException;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
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
@Named
@RequestScoped
@WebServlet(name = "getAlbums", urlPatterns = {"/getAlbums"})
public class getAlbums extends HttpServlet {
        @Inject
        private DAO dao;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int currentPage;
        int offset;
        int itemPerPage = 5;
        int totalPages;


        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
            currentPage = 2;
            offset = (currentPage - 1) * itemPerPage;
        } else {
            currentPage = 1;
            offset = 0;
        }
        int totalRows = dao.findAll(new Album()).size();
        if (totalRows > itemPerPage) {
            totalPages = (int) Math.ceil((totalRows * 1.0) / itemPerPage);
        } else {
            totalPages = 1;
        }
        List<Album> list = dao.findWithLimit(new Album(), offset, itemPerPage);
        Internationalization internationalization = new Internationalization();
        request.setAttribute("internationalization", internationalization);
        request.setAttribute("albumList", list);
        request.setAttribute("totalPage", totalPages);
        request.setAttribute("currentPage", currentPage);
        RequestDispatcher view = request.getRequestDispatcher("Albums.jsp");
        view.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
