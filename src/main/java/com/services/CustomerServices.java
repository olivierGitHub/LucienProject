package com.services;

import com.dao.dao.CustomerAccountDaoImpl;
import com.dao.dao.CustomerDaoImpl;
import com.dao.dao.interfaces.CustomerAccountDao;
import com.dao.dao.interfaces.CustomerDao;
import com.dao.entities.Customer;
import com.dao.entities.CustomerAccount;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * Created by olivier on 21/11/15.
 */
@Path("/customer")
public class CustomerServices {

    CustomerDao customerDao = new CustomerDaoImpl();
    CustomerAccountDao customerAccountDao = new CustomerAccountDaoImpl();


    @POST
    @Path("/create")
    @Produces(MediaType.TEXT_HTML)
    public String createCustomer(@QueryParam("firstname") String firstname,
                                 @QueryParam("lastname") String lastname,
                                 @QueryParam("account") String account  ){
        Customer newCustomer = new Customer();
            newCustomer.setFirstname(firstname);
            newCustomer.setLastname(lastname);
            newCustomer.setBirthday(new Date());
        boolean newCustomerOk = customerDao.create(newCustomer);
        if (account.equals("true")){
            CustomerAccount newCustomerAccount = new CustomerAccount();
                newCustomerAccount.setPosition(100.0); //100 euros when account get created, customer can add more after
                newCustomerAccount.setCustomer(newCustomer);
            customerAccountDao.create(newCustomerAccount);
        }

        if (newCustomerOk)
            return "<p>The customer created name is <b> " +
                firstname + " " + lastname +"</b>.</p>";
        else
            return "<p>Customer not created</p>";
    }


    @GET
    @Path("/read")
    @Produces(MediaType.TEXT_HTML)
    public String readCustomer(@QueryParam("id") Integer id){
        Customer customer = customerDao.read(id);

        if (customer!=null)
            return "<p>The customer name is <b> " +
                    customer.getFirstname() + " " +
                    customer.getLastname() +"</b>.</p>";
        else
            return "<p>Customer not read. Make sure to type a valid customer id</p>";
    }


    @PUT
    @Path("/update")
    @Produces(MediaType.TEXT_HTML)
    public String updateCustomer(@QueryParam("id") Integer id,
                               @QueryParam("firstname") String firstname,
                               @QueryParam("lastname") String lastname){
        Customer customerToUpdate = customerDao.read(id);
            customerToUpdate.setFirstname(firstname);
            customerToUpdate.setLastname(lastname);
        customerDao.update(customerToUpdate);
        if (!customerToUpdate.getFirstname().equals(firstname))
            return "<p>The customer updated parameters are:</p> " +
                    "<p>firstname: " + customerDao.read(id).getFirstname() + " </p>" +
                    "<p>lastname: " + customerDao.read(id).getLastname() + " </p>" ;
        else
            return "<p>Customer not updated. Make sure to type a valid customer id</p>";
    }


    @DELETE
    @Path("/delete")
    @Produces(MediaType.TEXT_HTML)
    public String deleteCustomer(@QueryParam("id") Integer id){
        Customer customerToDelete = customerDao.read(id);
        customerDao.delete(customerToDelete);

        if (customerDao.read(id) == null)
            return "<p>The customer has been deleted properly</p>";
        else
            return "<p>Customer not deleted. Make sure to type a valid customer id</p>";
    }

}
