package com.mfgshopapi.model;

import javax.persistence.*;

@Entity
@Table(name = "size_product")
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_size")
    private int id;
    @Column(name = "name_size", columnDefinition = "varchar(45)")
    private String nameSize;

    public Size() {
    }

    public Size(int id, String nameSize) {
        this.id = id;
        this.nameSize = nameSize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameSize() {
        return nameSize;
    }

    public void setNameSize(String nameSize) {
        this.nameSize = nameSize;
    }
}
