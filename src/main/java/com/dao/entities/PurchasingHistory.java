package com.dao.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by olivier on 21/11/15.
 */
@Entity
public class PurchasingHistory {

    @Id
    @GeneratedValue
    private Integer idPurchasingHistory;
    @OneToOne
    private CustomerAccount customerAccount;
    @OneToOne
    private Cashier cashier;
    private String PurchasingOption;

    public Integer getIdPurchasingHistory() {return idPurchasingHistory;}

    public CustomerAccount getCustomerAccount() {return customerAccount;}
    public void setCustomerAccount(CustomerAccount customerAccount) {this.customerAccount = customerAccount;}

    public Cashier getCashier() {return cashier;}
    public void setCashier(Cashier cashier) {this.cashier = cashier;}

    public String getPurchasingOption() {return PurchasingOption;}
    public void setPurchasingOption(String purchasingOption) {PurchasingOption = purchasingOption;}

}
