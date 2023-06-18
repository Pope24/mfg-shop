package com.mfgshopapi.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private int idProduct;
    @Column(name = "name_product", columnDefinition = "varchar(255)")
    private String nameProduct;
    @Column(name = "pre_cost", columnDefinition = "decimal(12, 0)")
    private BigDecimal prevCost;
    @Column(name = "curr_cost", columnDefinition = "decimal(12, 0)")
    private BigDecimal currCost;
    @Column(name = "amount_availble")
    private int amountAvailble;
    @Column(name = "label_product", columnDefinition = "varchar(255)")
    private String labelProduct;
    @Column(name = "desc_product", columnDefinition = "mediumtext")
    private String descProduct;
    @Column(name = "material", columnDefinition = "varchar(255)")
    private String material;
    @Column(name = "graphic", columnDefinition = "varchar(255)")
    private String graphic;
    @ManyToOne
    private TypeProduct typeProduct;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "size_product_product", joinColumns = @JoinColumn(name = "id_product"),
            inverseJoinColumns = @JoinColumn(name = "id_size"))
    Set<Size> sizes = new HashSet<>();
    @OneToMany
    List<ImgProduct> imgProducts;
    @Column(name = "color_product", columnDefinition = "varchar(45)")
    private String color;
    public Product() {
    }

    public Product(int idProduct, String nameProduct, BigDecimal prevCost, BigDecimal currCost, int amountAvailble, String labelProduct, String descProduct, String material, String graphic, TypeProduct typeProduct, Set<Size> sizes, List<ImgProduct> imgProducts, String color) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.prevCost = prevCost;
        this.currCost = currCost;
        this.amountAvailble = amountAvailble;
        this.labelProduct = labelProduct;
        this.descProduct = descProduct;
        this.material = material;
        this.graphic = graphic;
        this.typeProduct = typeProduct;
        this.sizes = sizes;
        this.imgProducts = imgProducts;
        this.color = color;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public BigDecimal getPrevCost() {
        return prevCost;
    }

    public void setPrevCost(BigDecimal prevCost) {
        this.prevCost = prevCost;
    }

    public BigDecimal getCurrCost() {
        return currCost;
    }

    public void setCurrCost(BigDecimal currCost) {
        this.currCost = currCost;
    }

    public int getAmountAvailble() {
        return amountAvailble;
    }

    public void setAmountAvailble(int amountAvailble) {
        this.amountAvailble = amountAvailble;
    }

    public String getLabelProduct() {
        return labelProduct;
    }

    public void setLabelProduct(String labelProduct) {
        this.labelProduct = labelProduct;
    }

    public String getDescProduct() {
        return descProduct;
    }

    public void setDescProduct(String descProduct) {
        this.descProduct = descProduct;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getGraphic() {
        return graphic;
    }

    public void setGraphic(String graphic) {
        this.graphic = graphic;
    }

    public TypeProduct getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(TypeProduct typeProduct) {
        this.typeProduct = typeProduct;
    }

    public Set<Size> getSizes() {
        return sizes;
    }

    public void setSizes(Set<Size> sizes) {
        this.sizes = sizes;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<ImgProduct> getImgProducts() {
        return imgProducts;
    }

    public void setImgProducts(List<ImgProduct> imgProducts) {
        this.imgProducts = imgProducts;
    }
}
