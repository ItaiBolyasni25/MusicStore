
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author maian
 */
@ViewScoped
@Named("AlbumPaginationBean")
public class AlbumPaginationBean implements Serializable {

    @Inject
   private DAO dao; 
    private List<Album> dataList;
    private int totalRows;
    private int currentPage;
    private int itemPerPage;
    private int totalPages;

    public AlbumPaginationBean() {
        this.itemPerPage = 5;
        this.currentPage = 1;
        this.totalRows = dao.findAll(new Album()).size();
    }

    public List<Album> getDatalist() {
        return dataList;
    }

    public void setDatalist(List<Album> dataList) {
        this.dataList = dataList;
    }

    public int getOffset() {
        return (currentPage - 1) * itemPerPage;
    }

    public int getTotalrows() {
        return totalRows;
    }

    public void setTotalrows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getItem_per_page() {
        return itemPerPage;
    }

    public void setItem_per_page(int itemPerPage) {
        this.itemPerPage = itemPerPage;
    }

    public int getTotal_pages() {
        return totalPages;
    }

    public void setTotal_pages(int totalPages) {
        this.totalPages = totalPages;
    }

    @PostConstruct
    public void init() {
        if (totalRows > itemPerPage) {
            totalPages = (int) Math.ceil((totalRows * 1.0) / itemPerPage);
        } else {
            totalPages = 1;
        }
        updateView();
    }

    public int getCurrent_page() {
        return this.currentPage;
    }

    public void setCurrent_page(int newCurrentPage) {
        System.out.println("aaa..."+newCurrentPage);
        currentPage = newCurrentPage;
        updateView();
    }
    
    public void updateView() {
       /* FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        if (!params.get("currentPage").isEmpty() || params.get("currentPage") != null) {
            currentPage = Integer.parseInt(params.get("currentPage"));
        }*/

        int offset = getOffset();
        setDatalist(dao.findWithLimit(new Album(), offset, itemPerPage));
    }

    public void next() {
        if (this.currentPage < totalPages) {
            this.currentPage++;
        }

        updateView();
    }

    public void prev() {
        if (this.currentPage > 1) {
            this.currentPage--;
        }

        updateView();
    }

}
