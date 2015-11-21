package com.dao.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by olivier on 21/11/15.
 */
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Integer idCustomer;
    private String firstname;
    private String lastname;
    private Date birthday;

    public int getIdCustomer() {return idCustomer;}

    public String getFirstname() {return firstname;}
    public void setFirstname(String firstname) {this.firstname = firstname;}

    public String getLastname() {return lastname;}
    public void setLastname(String lastname) {this.lastname = lastname;}

    public Date getBirthday() {return birthday;}
    public void setBirthday(Date birthday) {this.birthday = birthday;}

}
