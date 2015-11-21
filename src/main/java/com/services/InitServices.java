package com.services;

import com.dao.dao.accountingProvider.FundsDaoImpl;
import com.dao.dao.accountingProvider.ShopStockDaoImpl;
import com.dao.dao.customer.CustomerAccountDaoImpl;
import com.dao.dao.customer.CustomerDaoImpl;
import com.dao.dao.employee.EmployeeDaoImpl;
import com.dao.dao.gas.GasPumpDaoImpl;
import com.dao.dao.gas.GasTankDaoImpl;
import com.dao.dao.interfaces.accountingProvider.FundsDao;
import com.dao.dao.interfaces.accountingProvider.ShopStockDao;
import com.dao.dao.interfaces.customer.CustomerAccountDao;
import com.dao.dao.interfaces.customer.CustomerDao;
import com.dao.dao.interfaces.employee.EmployeeDao;
import com.dao.dao.interfaces.gas.GasPumpDao;
import com.dao.dao.interfaces.gas.GasTankDao;
import com.dao.entities.accountingProvider.Funds;
import com.dao.entities.accountingProvider.Provider;
import com.dao.entities.accountingProvider.ShopStock;
import com.dao.entities.customer.Customer;
import com.dao.entities.customer.CustomerAccount;
import com.dao.entities.employee.Employee;
import com.dao.entities.gas.GasPump;
import com.dao.entities.gas.GasTank;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * Created by olivier on 20/11/15.
 */
@Path("init")
public class InitServices {


    GasPumpDao gasPumpDao = new GasPumpDaoImpl();
    GasPump totalLucien = new GasPump();
    GasPump elfLucien = new GasPump();

    GasTankDao gasTankDao = new GasTankDaoImpl();
    GasTank parisTank = new GasTank();

    EmployeeDao employeeDao = new EmployeeDaoImpl();
    Employee batman = new Employee();
    Employee superman = new Employee();

    CustomerDao customerDao = new CustomerDaoImpl();
    Customer jack = new Customer();
    Customer lois = new Customer();

    CustomerAccountDao customerAccountDao = new CustomerAccountDaoImpl();
    CustomerAccount jackAccount = new CustomerAccount();
    CustomerAccount loisAccount = new CustomerAccount();

    FundsDao fundsDao = new FundsDaoImpl();
    Funds novemberFunds = new Funds();
    Funds decemberFunds = new Funds();

    ShopStockDao shopStockDao = new ShopStockDaoImpl();
    ShopStock marsChocolate = new ShopStock();
    ShopStock snickersChocolate = new ShopStock();

    Provider nestle = new Provider();


    {
        novemberFunds.setAmount(1200.0);
        decemberFunds.setAmount(1400.0);

        nestle.setName("Nestle");
        nestle.setAddress("Paris");

        marsChocolate.setProvider(nestle);
        marsChocolate.setArticle("Mars Chocolate");
        marsChocolate.setQuantity(120);
        marsChocolate.setUnitPrice(1.2);

        snickersChocolate.setProvider(nestle);
        snickersChocolate.setArticle("Snickers Chocolate");
        snickersChocolate.setQuantity(10);
        snickersChocolate.setUnitPrice(1.4);

        jack.setFirstname("jack");
        jack.setLastname("sparrow");
        jack.setBirthday(new Date());

        jackAccount.setCustomer(jack);
        jackAccount.setPosition(2540.0);

        lois.setFirstname("lois");
        lois.setLastname("lane");
        lois.setBirthday(new Date());

        loisAccount.setCustomer(lois);
        loisAccount.setPosition(20265.0);

        batman.setFirstname("bruce");
        batman.setLastname("wayne");
        batman.setBirthday(new Date());
        batman.setOccupation("caissier");

        superman.setFirstname("clark");
        superman.setLastname("kent");
        superman.setBirthday(new Date());
        superman.setOccupation("polyvalent");

        parisTank.setName("TankLucien");
        parisTank.setAddress("Paris");
        parisTank.setCapacity(1000000);
        parisTank.setActualState(80000);
        parisTank.setEmployee(batman);

        totalLucien.setName("TotalLucienStyle");
        totalLucien.setAddress("Paris");
        totalLucien.setCapacity(1000);
        totalLucien.setActualState(800);
        totalLucien.setEmployee(batman);
        totalLucien.setGasTank(parisTank);

        elfLucien.setName("ElfLucienStyle");
        elfLucien.setAddress("Paris");
        elfLucien.setCapacity(1200);
        elfLucien.setActualState(500);
        elfLucien.setEmployee(superman);
        elfLucien.setGasTank(parisTank);

    }

    @GET
    @Path("/database")
    @Produces(MediaType.TEXT_HTML)
    public String doInitialization(){

        boolean batmanOk = employeeDao.create(batman);
        boolean supermanOk = employeeDao.create(superman);
        boolean parisTankOk = gasTankDao.create(parisTank);
        boolean totalLucienOk = gasPumpDao.create(totalLucien);
        boolean elfLucienOk = gasPumpDao.create(elfLucien);
        boolean jackOk = customerDao.create(jack);
        boolean jackAccountOk = customerAccountDao.create(jackAccount);
        boolean loisOk = customerDao.create(lois);
        boolean loisAccountOk = customerAccountDao.create(loisAccount);

        if (batmanOk && supermanOk && parisTankOk && totalLucienOk && elfLucienOk
                && jackOk && jackAccountOk && loisOk && loisAccountOk)
            return "<p>Database's initialization is OK if the name above corresponds to <b>Batman</b></p><p>name: <b>"
                    + gasPumpDao.read(1).getEmployee().getFirstname()
                    + " " + gasPumpDao.read(1).getEmployee().getLastname()
                    + "</b>.</p><br/>"
                    + "<p>Database's initialization is OK if the name above corresponds to <b>Superman 's girlfriend</b></p><p>name: <b>"
                    + customerAccountDao.read(2).getCustomer().getFirstname()
                    + " " + customerAccountDao.read(2).getCustomer().getLastname()
                    + "</b>.</p><br/>"
                    + "<p>Note: running the initialization service a second time will logically fail ;-)</p>";
        else
            return "<p>Database's initialization: <b>already done</b></p>";
    }

    @GET
    @Path("/check")
    @Produces(MediaType.TEXT_HTML)
    public String doCheck(){
        Employee spiderman = new Employee();
            spiderman.setFirstname("peter");
            spiderman.setLastname("parker");
            spiderman.setBirthday(new Date());
            spiderman.setOccupation("multitache");
        boolean spidermanOk = employeeDao.create(spiderman);

        if (spidermanOk)
            return "<p>The pump named <b>" + gasPumpDao.read(1).getName() + "</b> is used by <b>"
                    + gasPumpDao.read(1).getEmployee().getFirstname()
                    + " " + gasPumpDao.read(1).getEmployee().getLastname()
                    + "</b>.</p>"
                    + "<p>The tank named <b>" + gasTankDao.read(1).getName() + "</b> is used by <b>"
                    + gasTankDao.read(1).getEmployee().getFirstname()
                    + " " + gasTankDao.read(1).getEmployee().getLastname()
                    + "</b>.</p><br/>"
                    + "<p>The employee above should correspond to <b>Spiderman</b> and is linked to nothing</p><p>name: <b>"
                    + employeeDao.read(3).getFirstname()
                    + " " + employeeDao.read(3).getLastname()
                    + "</b>.</p>";
        else
            return "<p>Database's checking: <b>KO</b></p>";
    }

}
