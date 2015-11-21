package com.dao.entities.gas;

import com.dao.entities.employee.Employee;

import javax.persistence.*;

/**
 * Created by olivier on 20/11/15.
 */
@Entity
public class GasTank {

    @Id
    @GeneratedValue
    private int idGasTank;
    private String name;
    private String address;
    private Integer capacity;
    private Integer actualState;
    //Foreign Keys
    @ManyToOne(fetch= FetchType.LAZY)
    private Employee employee;
    ;


    public int getIdGasTank() {return idGasTank;}

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

}
