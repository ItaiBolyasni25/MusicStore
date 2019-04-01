/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private String folderAbs = "C:\\Users\\Gabriela\\Desktop\\csdmusicstore\\sound++\\src\\main\\webapp\\assets\\banners\\";
    private String folder = "assets/bannners/";
    
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

    public void saveImage() {
        try (InputStream input = uploadedFile.getInputStream()) {
            String fileName = uploadedFile.getSubmittedFileName().substring(uploadedFile.getSubmittedFileName().lastIndexOf('\\') + 1);
            Files.copy(input, new File(folderAbs, fileName).toPath());
            Banner banner = new Banner();
            banner.setUsed("1");
            banner.setBanner(folder + fileName);
            dao.write(banner);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
