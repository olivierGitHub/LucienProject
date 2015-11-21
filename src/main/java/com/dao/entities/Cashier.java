package com.dao.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by olivier on 21/11/15.
 */
@Entity
public class Cashier {

    @Id
    @GeneratedValue
    private Integer idCashier;
    private String location;
    private Double actualState;

    public Integer getIdCashier() {return idCashier;}

    public String getLocation() {return location;}
    public void setLocation(String location) {this.location = location;}

    public Double getActualState() {return actualState;}
    public void setActualState(Double actualState) {this.actualState = actualState;}

}
