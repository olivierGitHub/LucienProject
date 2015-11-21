package com.dao.entities.accountingProvider;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by olivier on 21/11/15.
 */
public class Order {

    @Id
    @GeneratedValue
    private Integer idOrder;
    private Double amount;
    private Date PurchasingDate;
    private Boolean paid;
    @OneToOne
    private Funds funds;
    @ManyToMany
    private Provider provider;
    @OneToMany
    private ShopStock shopStock;


    public Integer getIdOrder() {return idOrder;}

    public Double getAmount() {return amount;}
    public void setAmount(Double amount) {this.amount = amount;}

    public Date getPurchasingDate() {return PurchasingDate;}
    public void setPurchasingDate(Date purchasingDate) {PurchasingDate = purchasingDate;}

    public Funds getFunds() {return funds;}
    public void setFunds(Funds funds) {this.funds = funds;}

    public Provider getProvider() {return provider;}
    public void setProvider(Provider provider) {this.provider = provider;}

    public ShopStock getShopStock() {return shopStock;}
    public void setShopStock(ShopStock shopStock) {this.shopStock = shopStock;}

    public Boolean getPaid() {return paid;}
    public void setPaid(Boolean paid) {this.paid = paid;}

}
