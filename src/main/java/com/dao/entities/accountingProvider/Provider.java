package com.dao.entities.accountingProvider;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by olivier on 21/11/15.
 */
@Entity
public class Provider {

    @Id
    @GeneratedValue
    private Integer idProvider;
    private String name;
    private String address;

    public Integer getIdProvider() {return idProvider;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

}
