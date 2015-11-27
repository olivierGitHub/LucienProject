package com.services;

import com.dao.dao.accountingProvider.*;
import com.dao.dao.interfaces.accountingProvider.*;
import com.dao.entities.accountingProvider.Funds;
import com.dao.entities.accountingProvider.OrderStock;
import com.dao.entities.accountingProvider.Provider;
import com.dao.entities.accountingProvider.ShopStock;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * Created by olivier on 21/11/15.
 */
@Path("/stock")
public class StockServices {

    FundsDao fundsDao = new FundsDaoImpl();
    OrderDao orderDao = new OrderDaoImpl();
    ShopStockDao shopStockDao = new ShopStockDaoImpl();

    @POST
    @Path("/order")
    @Produces(MediaType.TEXT_HTML)
    public String orderStock(@QueryParam("id") int idShopStock, @QueryParam("quantity") int quantity){

        // get shop of the required article
        ShopStock shopStock = shopStockDao.read(idShopStock);

        // get provider of the required article
        Provider provider = shopStockDao.read(idShopStock).getProvider();

        //calculate the price to pay
        Double price = shopStockDao.read(idShopStock).getUnitPrice() * quantity;

        // update funds
        Double oldAmount = fundsDao.read(1).getAmount();
        Funds funds = fundsDao.read(1);
            funds.setAmount(oldAmount - price);
        fundsDao.update(funds);

        // create the orderStock
        OrderStock order = new OrderStock();
            order.setShopStock(shopStock);
            order.setAmount(price);
            order.setProvider(provider);
            order.setFunds(fundsDao.read(1));
            order.setPurchasingDate(new Date());
            order.setPaid(false);
        boolean orderOk = orderDao.create(order);

        // update stock
        int actualQuantity = shopStock.getQuantity();
        shopStock.setQuantity(actualQuantity + quantity);
        shopStockDao.update(shopStock);

        if (actualQuantity!=shopStock.getQuantity())
            return "<p>OrderStock : <b>" + shopStockDao.read(idShopStock).getArticle() +"</b></p>" +
                   "<p>OrderStock quantity: <b>" + quantity +"</b></p>" +
                   "<p>OrderStock amount: <b>" + order.getAmount() +"</b> €</p>" +
                   "<p>OrderStock is paid : <b>" + order.getPaid() +"</b></p>" +
                   "<p>OrderStock funds remaining : <b>" + order.getFunds().getAmount() +"</b></p>" +
                   "<p>Stock remaining : <b>" + shopStockDao.read(idShopStock).getQuantity() +"</b></p>";
        else
            return "<p>The orderStock failed, make sure to type a valid <b>id</b>.</p>";
    }

    @GET
    @Path("/pay")
    @Produces(MediaType.TEXT_HTML)
    public String payOrder(@QueryParam("id") int id){
        //Pay the orderStock
            return "<p>Order paid: true</p>";
    }

}
