package com.mfgshopapi.model;

import javax.persistence.*;

@Entity
@Table(name = "img_product")
public class ImgProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_img")
    private int id;
    @Column(name = "path_img", columnDefinition = "mediumtext")
    private String pathImg;

    public ImgProduct() {
    }

    public ImgProduct(int id, String pathImg) {
        this.id = id;
        this.pathImg = pathImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPathImg() {
        return pathImg;
    }

    public void setPathImg(String pathImg) {
        this.pathImg = pathImg;
    }
}
