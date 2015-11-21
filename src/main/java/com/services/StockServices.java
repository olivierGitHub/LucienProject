package com.services;

import com.dao.dao.accountingProvider.*;
import com.dao.dao.interfaces.accountingProvider.*;
import com.dao.entities.accountingProvider.Order;
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

        // get provider of the required article
        Provider provider = shopStockDao.read(idShopStock).getProvider();

        //calculate the price to pay
        Double price = shopStockDao.read(idShopStock).getUnitPrice() * quantity;

        // create the order
        Order order = new Order();
            order.setShopStock(shopStockDao.read(idShopStock));
            order.setAmount(price);
            order.setProvider(provider);
            order.setFunds(fundsDao.read(1));
            order.setPurchasingDate(new Date());
            order.setPaid(false);
        boolean orderOk = orderDao.create(order);

        // update stock
        ShopStock shopStock = shopStockDao.read(idShopStock);
        Integer actualQuantity = shopStock.getQuantity();
        shopStock.setQuantity(actualQuantity + quantity);
        shopStockDao.update(shopStock);

        if (orderOk && actualQuantity!=shopStock.getQuantity())
            return "<p>Si un resultat s'affiche, la config est OK</p>";
        else
            return null;
    }

    @PUT
    @Path("/pay")
    @Produces(MediaType.TEXT_HTML)
    public String payOrder(@QueryParam("orderId") int orderId){
        //Pay the order
        Order orderToPay = orderDao.read(orderId);
        orderToPay.setPaid(true);
        orderDao.update(orderToPay);

        if (orderDao.read(orderId).getPaid())
            return "<p>Si un resultat s'affiche, la config est OK</p>";
        else
            return null;
    }

}
