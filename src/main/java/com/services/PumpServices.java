package com.services;

import com.dao.dao.customer.CustomerAccountDaoImpl;
import com.dao.dao.gas.GasPumpDaoImpl;
import com.dao.dao.gas.GasTankDaoImpl;
import com.dao.dao.gas.PurchasingHistoryDaoImpl;
import com.dao.dao.interfaces.customer.CustomerAccountDao;
import com.dao.dao.interfaces.gas.GasPumpDao;
import com.dao.dao.interfaces.gas.GasTankDao;
import com.dao.dao.interfaces.gas.PurchasingHistoryDao;
import com.dao.entities.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Created by olivier on 20/11/15.
 */
@Path("/pump")
public class PumpServices {

    GasPumpDao gasPumpDao = new GasPumpDaoImpl();
    GasTankDao gasTankDao = new GasTankDaoImpl();
    CustomerAccountDao customerAccountDao = new CustomerAccountDaoImpl();
    PurchasingHistoryDao purchasingHistoryDao = new PurchasingHistoryDaoImpl();

    final Double fuelRate = 1.2; // 1.2€/L


    @GET
    @Path("/buy")
    @Produces(MediaType.TEXT_HTML)
    public String doOperationService(@QueryParam("customerId") int customerId, @QueryParam("quantity") int quantity){

        //Update of the state of the pump after the purchase
        int actualPump = gasPumpDao.read(1).getActualState();
        GasPump gasPump = gasPumpDao.read(1);
            gasPump.setActualState(actualPump - quantity);
        gasPumpDao.update(gasPump);

        //Update of the state of the tank after the purchase
        int actualTank = gasTankDao.read(1).getActualState();
        GasTank gasTank = gasTankDao.read(1);
        gasTank.setActualState(actualTank - quantity);
        gasTankDao.update(gasTank);

        //Update of the state of the customer account after the purchase
        double price = quantity * fuelRate;
        double actualPosition = customerAccountDao.read(customerId).getPosition();
        CustomerAccount customerAccount = customerAccountDao.read(customerId);
            customerAccount.setPosition(actualPosition - price);
        customerAccountDao.update(customerAccount);

        //Save in the history of purchase
        PurchasingHistory purchasingHistory = new PurchasingHistory();
            purchasingHistory.setCustomerAccount(customerAccount);
            purchasingHistory.setPurchasingOption("Using Customer Account");
        purchasingHistoryDao.create(purchasingHistory);


        return "<p>Si un resultat s'affiche, la config est OK</p>";
        //return res;
    }


}
