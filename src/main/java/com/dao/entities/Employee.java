package com.dao.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by olivier on 20/11/15.
 */
@Entity
public class Employee {

    @Id
    @GeneratedValue
    private int idEmployee;
    private String firstname;
    private String lastname;
    private Date birthday;
    private String occupation;
    @ManyToOne(fetch=FetchType.LAZY)
    private Employee manager;


    public int getIdEmployee() {return idEmployee;}

    public String getFirstname() {return firstname;}
    public void setFirstname(String firstname) {this.firstname = firstname;}

    public String getLastname() {return lastname;}
    public void setLastname(String lastname) {this.lastname = lastname;}

    public Date getBirthday() {return birthday;}
    public void setBirthday(Date birthday) {this.birthday = birthday;}

    public String getOccupation() {return occupation;}
    public void setOccupation(String occupation) {this.occupation = occupation;}

    public Employee getManager() {return manager;}

}
