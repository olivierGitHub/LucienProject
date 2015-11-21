package com.dao.entities.accountingProvider;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by olivier on 21/11/15.
 */
@Entity
public class Funds {

    @Id
    @GeneratedValue
    private Integer idFunds;
    private Double amount;

    public Integer getIdFunds() {return idFunds;}

    public Double getAmount() {return amount;}
    public void setAmount(Double amount) {this.amount = amount;}

}
