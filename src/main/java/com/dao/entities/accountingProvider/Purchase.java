package com.dao.entities.accountingProvider;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.Date;

/**
 * Created by olivier on 21/11/15.
 */
public class Purchase {

    @Id
    @GeneratedValue
    private Integer idPurchase;
    private Double amount;
    private Date PurchasingDate;
    @OneToOne
    private Invoice invoice;
    @OneToOne
    private Funds funds;
    @ManyToMany
    private Provider provider;


    public Integer getIdPurchase() {return idPurchase;}

    public Double getAmount() {return amount;}
    public void setAmount(Double amount) {this.amount = amount;}

    public Date getPurchasingDate() {return PurchasingDate;}
    public void setPurchasingDate(Date purchasingDate) {PurchasingDate = purchasingDate;}

    public Invoice getInvoice() {return invoice;}
    public void setInvoice(Invoice invoice) {this.invoice = invoice;}

    public Funds getFunds() {return funds;}
    public void setFunds(Funds funds) {this.funds = funds;}

    public Provider getProvider() {return provider;}
    public void setProvider(Provider provider) {this.provider = provider;}

}
