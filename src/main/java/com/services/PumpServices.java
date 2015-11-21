package com.services;

import com.dao.dao.customer.CustomerAccountDaoImpl;
import com.dao.dao.gas.GasPumpDaoImpl;
import com.dao.dao.gas.GasTankDaoImpl;
import com.dao.dao.gas.PurchasingHistoryDaoImpl;
import com.dao.dao.interfaces.customer.CustomerAccountDao;
import com.dao.dao.interfaces.gas.GasPumpDao;
import com.dao.dao.interfaces.gas.GasTankDao;
import com.dao.dao.interfaces.gas.PurchasingHistoryDao;
import com.dao.entities.customer.CustomerAccount;
import com.dao.entities.gas.GasPump;
import com.dao.entities.gas.GasTank;
import com.dao.entities.gas.PurchasingHistory;

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
    public String purchaseFuel(@QueryParam("accountId") int accountId, @QueryParam("quantity") int quantity){

        //Update of the state of the pump after the purchase
        int previousPumpState = gasPumpDao.read(1).getActualState();
        GasPump gasPump = gasPumpDao.read(1);
            gasPump.setActualState(previousPumpState - quantity);
        gasPumpDao.update(gasPump);

        //Update of the state of the tank after the purchase
        int previousTankState = gasTankDao.read(1).getActualState();
        GasTank gasTank = gasTankDao.read(1);
            gasTank.setActualState(previousTankState - quantity);
        gasTankDao.update(gasTank);

        //Update of the state of the customer account after the purchase
        double price = quantity * fuelRate;
        double previousPosition = customerAccountDao.read(accountId).getPosition();
        CustomerAccount customerAccount = customerAccountDao.read(accountId);
            customerAccount.setPosition(previousPosition - price);
        customerAccountDao.update(customerAccount);

        //Save in the history of purchase
        PurchasingHistory purchasingHistory = new PurchasingHistory();
            purchasingHistory.setCustomerAccount(customerAccount);
            purchasingHistory.setPurchasingOption("Using Customer Account");
        purchasingHistoryDao.create(purchasingHistory);

        if ( (previousPumpState!=gasPumpDao.read(1).getActualState())
                && (previousTankState!=gasTankDao.read(1).getActualState())
                && (previousPosition!=customerAccountDao.read(accountId).getPosition()) )
            return "<p><b>Before</b> the fuel purchase, the pump <b>"+ gasPump.getName()+"</b> contains : <b>" + previousPumpState +"</b></p>" +
                   "<p><b>After</b> the fuel purchase, the pump <b>"+ gasPump.getName()+"</b> contains : <b>" + gasPumpDao.read(1).getActualState() +"</b></p><br />" +
                    "<p><b>Before</b> the fuel purchase, the tank <b>"+ gasTank.getName()+"</b> contains : <b>" + previousTankState +"</b></p>" +
                    "<p><b>After</b> the fuel purchase, the tank <b>"+ gasTank.getName()+"</b> contains : <b>" + gasTankDao.read(1).getActualState() +"</b></p><br />" +
                    "<p><b>Before</b> the fuel purchase, the position of the account of <b>"+ customerAccount.getCustomer().getFirstname()+ " " + customerAccount.getCustomer().getLastname() +
                    "</b> was : <b>" + previousPosition +"</b></p>" +
                    "<p><b>After</b> the fuel purchase, the position of the account of <b>"+ customerAccount.getCustomer().getFirstname()+ " " + customerAccount.getCustomer().getLastname() +
                    "</b> is : <b>" + customerAccountDao.read(accountId).getPosition() +"</b></p>";
        else
            return "<p>The fuel purchase failed, make sure to type a valid <b>customer account id</b>.</p>";
    }

}
