package com.dao.entities.customer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by olivier on 21/11/15.
 */
@Entity
public class CustomerAccount {

    @Id
    @GeneratedValue
    private Integer idCustomerAccount;
    @OneToOne
    private Customer customer;
    private Double position;

    public Integer getIdCustomerAccount() {return idCustomerAccount;}

    public Customer getCustomer() {return customer;}
    public void setCustomer(Customer customer) {this.customer = customer;}

    public Double getPosition() {return position;}
    public void setPosition(Double position) {this.position = position;}

}
