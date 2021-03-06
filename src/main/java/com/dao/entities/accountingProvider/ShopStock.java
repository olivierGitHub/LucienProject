package com.dao.entities.accountingProvider;

import javax.persistence.*;

/**
 * Created by olivier on 21/11/15.
 */
@Entity
public class ShopStock {

    @Id
    @GeneratedValue
    private Integer idShopStock;
    private String article;
    private Double unitPrice;
    private Integer quantity;
    @OneToOne(cascade = {CascadeType.ALL})
    @PrimaryKeyJoinColumn
    private Provider provider;

    public Integer getIdShopStock() {return idShopStock;}

    public Provider getProvider() {return provider;}
    public void setProvider(Provider provider) {this.provider = provider;}

    public Integer getQuantity() {return quantity;}
    public void setQuantity(Integer quantity) {this.quantity = quantity;}

    public String getArticle() {return article;}
    public void setArticle(String article) {this.article = article;}

    public Double getUnitPrice() {return unitPrice;}
    public void setUnitPrice(Double unitPrice) {this.unitPrice = unitPrice;}

}
