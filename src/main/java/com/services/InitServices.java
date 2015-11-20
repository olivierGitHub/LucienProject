package com.services;

import com.dao.dao.EmployeeDaoImpl;
import com.dao.dao.GasPumpDaoImpl;
import com.dao.dao.GasTankDaoImpl;
import com.dao.dao.interfaces.EmployeeDao;
import com.dao.dao.interfaces.GasPumpDao;
import com.dao.dao.interfaces.GasTankDao;
import com.dao.entities.Employee;
import com.dao.entities.GasPump;
import com.dao.entities.GasTank;

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



    {

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
        if (batmanOk && supermanOk && parisTankOk && totalLucienOk && elfLucienOk)
            return "<p>Database's initialization is OK if the name above corresponds to <b>Batman</b></p><p>name: <b>"
                    + gasPumpDao.read(1).getEmployee().getFirstname()
                    + " " + gasPumpDao.read(1).getEmployee().getLastname()
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
            return "<p>The pump named " + gasPumpDao.read(1).getName() + " is used by <b>"
                    + gasPumpDao.read(1).getEmployee().getFirstname()
                    + " " + gasPumpDao.read(1).getEmployee().getLastname()
                    + "</b>.</p>"
                    + "<p>The tank named " + gasTankDao.read(1).getName() + " is used by <b>"
                    + gasTankDao.read(1).getEmployee().getFirstname()
                    + " " + gasTankDao.read(1).getEmployee().getLastname()
                    + "</b>.</p><br/>"
                    + "<p>The employee above should correspond to Spiderman and is linked to nothing</p><p>name: <b>"
                    + employeeDao.read(3).getFirstname()
                    + " " + employeeDao.read(3).getLastname()
                    + "</b>.</p>";
        else
            return "<p>Database's checking: <b>KO</b></p>";
    }

}