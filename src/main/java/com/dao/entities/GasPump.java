package com.dao.entities;

import javax.jws.Oneway;
import javax.persistence.*;

/**
 * Created by olivier on 20/11/15.
 */
@Entity
public class GasPump {

    @Id
    @GeneratedValue
    private int idGasPump;
    private String name;
    private String address;
    private Integer capacity;
    private Integer actualState;
    //Foreign Keys
    @ManyToOne(fetch= FetchType.LAZY)
    private Employee employee;
    @ManyToOne(fetch= FetchType.LAZY)
    private GasTank gasTank;


    public int getIdGasPump() {return idGasPump;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public Integer getActualState() {return actualState;}
    public void setActualState(Integer actualState) {this.actualState = actualState;}

    public Integer getCapacity() {return capacity;}
    public void setCapacity(Integer capacity) {this.capacity = capacity;}

    //Foreign Keys
    public Employee getEmployee() {return employee;}
    public void setEmployee(Employee employee) {this.employee = employee;}

    public GasTank getGasTank() {return gasTank;}
    public void setGasTank(GasTank gasTank) {this.gasTank = gasTank;}

}
