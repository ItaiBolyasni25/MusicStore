package com.mycompany.Controller;

import com.mycompany.Model.Album;
import com.mycompany.Model.Artist;
import com.mycompany.Model.Cart;
import com.mycompany.Model.InventoryReport;
import com.mycompany.Model.Invoice;
import com.mycompany.Model.PurchaseReport;
import com.mycompany.Model.SaleReport;
import com.mycompany.Model.Track;
import com.mycompany.Model.User;
import com.mycompany.Persistence.DAO;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author aantoine97
 */
@Named(value = "reportBean")
@SessionScoped
public class ReportBean implements Serializable {

    private Date startDate;
    private Date endDate;
    private String email;
    private boolean searched;
    private String pattern;
    private String artist;
    private String trackTitle;
    private List<Artist> artists;
    private List<Album> albums;
    private String dateRange;

    private DAO dao;
    
    @Inject
    public ReportBean(DAO dao) {
        this.dao = dao;
    }
    
    public ReportBean() {}

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    public List<InventoryReport> getTotalSales() throws ParseException {
        List<InventoryReport> reports = new ArrayList<>();
        if (dateRange != null) {
            String[] days = dateRange.split("-");

            List<Album> albums = dao.find(new Album(), "date_added between '" + newDateFormat(days[0]) + "' and '" + newDateFormat(days[1]) + "'");
            List<Track> tracks = dao.find(new Track(), "date_added between '" + newDateFormat(days[0]) + "' and '" + newDateFormat(days[1]) + "'");

            reports = getFullList(albums, tracks);
        }
        return reports;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }


    public List<InventoryReport> getSalesByClient() throws ParseException {
        List<InventoryReport> reports = new ArrayList<>();
        if (dateRange != null) {
            String[] days = dateRange.split("-");

            List<Cart> carts = dao.find(new Cart(), "invoice.invoice_id IS NOT NULL AND identifier.user.email = '" + email + "'");
            List<Album> albumsBought = new ArrayList<>();
            List<Track> tracksBought = new ArrayList<>();

            for (Cart cart : carts) {
                albumsBought.add(cart.getAlbum());
                tracksBought.add(cart.getTrack());
            }

            List<Album> albums = dao.find(new Album(), "date_added between '" + newDateFormat(days[0]) + "' and '" + newDateFormat(days[1]) + "'");

            List<Track> tracks = dao.find(new Track(), "date_added between '" + newDateFormat(days[0]) + "' and '" + newDateFormat(days[1]) + "'");

            reports = new ArrayList<>();

            for (Album album : albumsBought) {
                if (albums.contains(album)) {
                    reports.add(new InventoryReport("Album", album.getTitle(), album.getTotal_sales()));
                }
            }

            for (Track track : tracksBought) {
                if (tracks.contains(track)) {
                    reports.add(new InventoryReport("Track", track.getTitle(), track.getTotal_sales()));
                }
            }
        }
        return reports;

    }

    public void patternALbumChanged() {
        if (pattern != null && !pattern.isEmpty() && !pattern.equals("")) {
            albums = dao.findWithOnlyPatternAlbum(new Album(), pattern);
        }
    }

    public void patternArtistChanged() {
        if (artist != null && !artist.isEmpty() && !artist.equals("")) {
            System.out.println("12");
            artists = dao.findWithLimitPatternArtist(new Artist(), 0, 0, artist);
        }
    }

    public SaleReport getSalesByAlbums() throws ParseException {
        SaleReport s = new SaleReport();
        if (dateRange != null && pattern != null) {
            String[] days = dateRange.split("-");
            List<Invoice> invoices = dao.customFindDB(new Invoice(), "SELECT inv FROM Orders ord join ord.invoice inv join ord.album al where al.title = '" + this.pattern + "' AND inv.date BETWEEN '" + newDateFormat(days[0]) + "' AND '" + newDateFormat(days[1]) + "' ORDER BY inv.date ASC");
            s.setTotalSale(invoices.size());
            s.setInvoice(invoices);
        }
        return s;

    }

    public List<InventoryReport> getTopSellers() throws ParseException {
        List<InventoryReport> reports = new ArrayList<>();
        List<Object[]> albums = new ArrayList<Object[]>();
        List<Object[]> tracks = new ArrayList<Object[]>();
        if (dateRange != null) {
            String[] days = dateRange.split("-");
            albums = dao.customFindObject("SELECT al.title, count(inv) from Orders ord join ord.invoice "
                    + "inv join ord.album al where inv.date BETWEEN '" + newDateFormat(days[0]) + "' AND '"
                    + newDateFormat(days[1]) + "' GROUP BY al.album_id ORDER BY COUNT(inv) DESC");
            tracks = dao.customFindObject("SELECT tr.title, count(inv) from Orders ord join ord.invoice "
                    + "inv join ord.track tr where inv.date BETWEEN '" + newDateFormat(days[0]) + "' AND '"
                    + newDateFormat(days[1]) + "' GROUP BY tr.track_id ORDER BY COUNT(inv) DESC");
        }
        for (Object[] obj : albums) {
            InventoryReport ir = new InventoryReport();
            ir.setName(obj[0].toString());
            ir.setType("Album");
            ir.setSales(Integer.parseInt(obj[1].toString()));
            reports.add(ir);
        }
        for (Object[] obj : tracks) {
            InventoryReport ir = new InventoryReport();
            ir.setName(obj[0].toString());
            ir.setType("Track");
            ir.setSales(Integer.parseInt(obj[1].toString()));
            reports.add(ir);
        }

        return reports;

    }

    public List<PurchaseReport> getTopClient() throws ParseException {
        List<PurchaseReport> sr = new ArrayList<>();
        List<Object[]> users = new ArrayList<Object[]>();
        if (dateRange != null) {
            String[] days = dateRange.split("-");
            users = dao.customFindObject("SELECT u.email, count(inv) from Invoice "
                    + "inv join inv.user u where inv.date BETWEEN '" + newDateFormat(days[0]) + "' AND '"
                    + newDateFormat(days[1]) + "'  GROUP BY u.email HAVING COUNT(inv) > 0 ORDER BY COUNT(inv) DESC");
        }
        for (Object[] obj : users) {
            PurchaseReport ir = new PurchaseReport();
            ir.setUser(obj[0].toString());
            ir.setTotalPurchase(Integer.parseInt(obj[1].toString()));
            sr.add(ir);
        }
        return sr;
    }

    public List<Track> getZeroTracks() throws ParseException {
        List<Track> reports = new ArrayList<>();
        List<Object[]> tracks = new ArrayList<Object[]>();
        if (dateRange != null) {

            String[] days = dateRange.split("-");
            tracks = dao.customFindObject("SELECT tr.title, tr.track_id from Track tr Where tr.track_id NOT IN (Select tr.track_id from Orders ord join ord.invoice "
                    + "inv join ord.track tr where inv.date BETWEEN '" + newDateFormat(days[0]) + "' AND '"
                    + newDateFormat(days[1]) + "')");
        }
        for (Object[] obj : tracks) {

            Track ir = new Track();
            ir.setTitle(obj[0].toString());

            ir.setId(Integer.parseInt(obj[1].toString()));
            reports.add(ir);
        }
        return reports;

    }

    public List<User> getZeroClient() throws ParseException {
        List<User> reports = new ArrayList<>();
        List<Object[]> users = new ArrayList<Object[]>();
        if (dateRange != null) {
            String[] days = dateRange.split("-");
            users = dao.customFindObject("SELECT u.email, u.lastname, u.firstname from User u Where u.email NOT IN (Select u.email from Invoice "
                    + "inv join inv.user u where inv.date BETWEEN '" + newDateFormat(days[0]) + "' AND '"
                    + newDateFormat(days[1]) + "')");
        }
        for (Object[] obj : users) {

            User ir = new User();
            ir.setEmail(obj[0].toString());
            ir.setLastname(obj[1].toString());
            ir.setFirstname(obj[2].toString());
            reports.add(ir);
        }
        return reports;

    }

    // --- Getters and setters --- //
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSearched() {
        return searched;
    }

    public void setSearched(boolean searched) {
        this.searched = searched;
    }

    private java.sql.Date newDateFormat(String date) throws ParseException {
        String newString = "";
        String[] elements = date.split("/");
        newString += elements[2] + "-" + elements[0] + "-" + elements[1];

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date newdate = sdf1.parse(newString.replaceAll("\\s", ""));
        java.sql.Date sqlDate = new java.sql.Date(newdate.getTime());
        return sqlDate;
    }

    public List<InventoryReport> getSalesByArtist() throws ParseException {
        List<InventoryReport> reports = null;
        if (artist != null && dateRange != null) {
            String[] days = dateRange.split("-");
            List<Album> albums = dao.customFindDB(new Album(), "SELECT t from Album t inner join t.artists a "
                    + "where a.name = '" + artist + "' and (t.date_added between '" + newDateFormat(days[0]) + "' and '" + newDateFormat(days[1]) + "')");

            List<Track> tracks = dao.customFindDB(new Track(), "Select t from Track t inner join t.album a inner join a.artists art "
                    + "where art.name = '" + artist + "' and (t.date_added between '" + newDateFormat(days[0]) + "' and '" + newDateFormat(days[1]) + "')");

            reports = getFullList(albums, tracks);

        }
        return reports;
    }

    public List<InventoryReport> getSalesByTrack() {
        List<InventoryReport> reports = new ArrayList<>();
        if (dateRange != null) {
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            String stringStart = simpleDateFormat.format(startDate);
            String stringEnd = simpleDateFormat.format(endDate);

            List<Track> tracks = dao.find(new Track(), "title = '" + trackTitle + "' and "
                    + "(identifier.date_added between '" + stringStart + "' and '" + stringEnd + "')");

            reports = getFullList(new ArrayList<Album>(), tracks);
        }
        return reports;
    }

    // --- Helper methods --- //
    public List<InventoryReport> getFullList(List<Album> albums, List<Track> tracks) {
        List<InventoryReport> reports = new ArrayList<>();

        for (Album album : albums) {
            reports.add(new InventoryReport("Album", album.getTitle(), album.getTotal_sales()));
        }

        for (Track track : tracks) {
            reports.add(new InventoryReport("Track", track.getTitle(), track.getTotal_sales()));
        }

        return reports;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

}
