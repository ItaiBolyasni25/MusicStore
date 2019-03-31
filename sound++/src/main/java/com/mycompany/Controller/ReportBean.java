package com.mycompany.Controller;

import com.mycompany.Model.Album;
import com.mycompany.Model.InventoryReport;
import com.mycompany.Model.Track;
import com.mycompany.Persistence.DAO;
import java.io.Serializable;
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

    @Inject
    private DAO dao;

    public List<InventoryReport> getTotalSales() {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        
        String stringStart = simpleDateFormat.format(startDate);
        String stringEnd = simpleDateFormat.format(endDate);
        
        List<InventoryReport> reports = new ArrayList<>();
        List<Album> albums = dao.find(new Album(), "date_added between '" + stringStart + "' and '" + stringEnd + "'");
        List<Track> tracks = dao.find(new Track(), "date_added between '" + stringStart + "' and '" + stringEnd + "'");

        for (Album album : albums) {
            reports.add(new InventoryReport("Album", album.getTitle(), album.getTotal_sales()));
        }

        for (Track track : tracks) {
            reports.add(new InventoryReport("Track", track.getTitle(), track.getTotal_sales()));
        }
        
        setSearched(true);

        return reports;
    }
    
    public List<InventoryReport> getSalesByClient() {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        
        String stringStart = simpleDateFormat.format(startDate);
        String stringEnd = simpleDateFormat.format(endDate);
        
        List<InventoryReport> reports = new ArrayList<>();
        List<Album> albums = dao.customFind(new Album(), "join order o where t.order_id = o.order_id join invoice i where o.invoice_id = i.invoice_id join user u where i.user_id = u.user_id "
                + "where u.email = '" + email + "' and (t.date_added between '" + stringStart + "' and '" + stringEnd + "')");
        
        List<Track> tracks = dao.customFind(new Track(), "join order o where t.order_id = o.order_id join invoice i where o.invoice_id = i.invoice_id join user u where i.user_id = u.user_id "
                + "where u.email = '" + email + "' and (t.date_added between '" + stringStart + "' and '" + stringEnd + "')");

        for (Album album : albums) {
            reports.add(new InventoryReport("Album", album.getTitle(), album.getTotal_sales()));
        }

        for (Track track : tracks) {
            reports.add(new InventoryReport("Track", track.getTitle(), track.getTotal_sales()));
        } 
        
        setSearched(true);
        
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
}
