package com.dao.entities.accountingProvider;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

/**
 * Created by olivier on 21/11/15.
 */
public class Invoice {

    @Id
    @GeneratedValue
    private Integer idInvoice;
    private Boolean paid;
    private Date dueDate;

    public Integer getIdInvoice() {return idInvoice;}

    public Boolean getPaid() {return paid;}
    public void setPaid(Boolean paid) {this.paid = paid;}

    public Date getDueDate() {return dueDate;}
    public void setDueDate(Date dueDate) {this.dueDate = dueDate;}

}
