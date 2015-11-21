package com.dao.entities.accountingProvider;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by olivier on 21/11/15.
 */
public class ShopStock {

    @Id
    @GeneratedValue
    private Integer idShopStock;
    private String article;
    private Integer quantity;
    @OneToOne
    private Provider provider;

    public Integer getIdShopStock() {return idShopStock;}

    public Provider getProvider() {return provider;}
    public void setProvider(Provider provider) {this.provider = provider;}

    public Integer getQuantity() {return quantity;}
    public void setQuantity(Integer quantity) {this.quantity = quantity;}

    public String getArticle() {return article;}
    public void setArticle(String article) {this.article = article;}

}
