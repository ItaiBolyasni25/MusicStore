package com.mycompany.Controller;

import com.mycompany.Interface.EntityModel;
import com.mycompany.Model.Banner;
import com.mycompany.Persistence.DAO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.Part;

/**
 *
 * @author Gabriela
 */
@Named(value = "bannerAddBean")
@SessionScoped
public class BannerAddBean implements Serializable, EntityModel {

    private Part uploadedFile;
   //private String folder = "D:\\cygwin\\projects_w19\\TeamA\\banners\\";
    private String folder = "C:\\Users\\maian\\Desktop\\csdmusicstore\\sound++\\src\\main\\webapp\\assets\\banners";
    //private String folder = "D:\\cygwin\\projects_w19\\TeamA\\banners\\";
    //private String folder = "C:\\Users\\austi\\Desktop\\SchoolStuff\\JavaServerSide\\Project\\csdmusicstore\\sound++\\src\\main\\webapp\\assets\\banners";
    //private String folder = "D:\\cygwin\\projects_w19\\TeamA\\banners\\";

    private String messageError = null;

    @Inject
    private DAO dao;

    /**
     * Creates a new instance of BannerAddBean
     */
    public BannerAddBean() {
    }

    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }

    public String saveImage() {
        if (this.uploadedFile != null) {
            try (InputStream input = uploadedFile.getInputStream()) {
                String fileName = uploadedFile.getSubmittedFileName().substring(uploadedFile.getSubmittedFileName().lastIndexOf('\\') + 1);
                Files.copy(input, new File(folder, fileName).toPath());
                Banner banner = new Banner();
                banner.setUsed("1");
                unsetUsedBanner();
                banner.setBanner(fileName);
                dao.write(banner);
                this.messageError = null;
            } catch (IOException e) {
                e.printStackTrace();
                this.messageError = "";
                return "manager/bannerad.xhtml";
            }
        } else {
            this.messageError = "";
        }
        return "manager/bannerad.xhtml";
    }

    private void unsetUsedBanner() {
        List<Banner> banners = (List<Banner>) dao.find(new Banner(), "used LIKE '1'");
        for (int i = 0; i < banners.size(); i++) {
            banners.get(i).setUsed("0");
            dao.updateEntity(banners.get(i));
        }
    }

    public List<Banner> getBannersNotUsed() {
        return (List<Banner>) dao.find(new Banner(), "used LIKE '0'");
    }

    public Banner getCurrentBannerUsed() {
        return (Banner) dao.find(new Banner(), "used LIKE '1'").get(0);
    }
}
