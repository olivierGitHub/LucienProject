package com.services;

import com.dao.dao.employee.EmployeeDaoImpl;
import com.dao.dao.interfaces.employee.EmployeeDao;
import com.dao.entities.employee.Employee;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * Created by olivier on 21/11/15.
 */
@Path("/humanressources")
public class HumanRessourcesServices {

    EmployeeDao employeeDao = new EmployeeDaoImpl();


    @POST
    @Path("/create")
    @Produces(MediaType.TEXT_HTML)
    public String createEmployee(@QueryParam("firstname") String firstname,
                                 @QueryParam("lastname") String lastname,
                                 @QueryParam("occupation") String occupation ){
        Employee newEmployee = new Employee();
            newEmployee.setFirstname(firstname);
            newEmployee.setLastname(lastname);
            newEmployee.setBirthday(new Date());
            newEmployee.setOccupation(occupation);
        boolean newEmployeeOk = employeeDao.create(newEmployee);

        if (newEmployeeOk)
            return "<p>The employee created name is <b> " +
                    firstname + " " + lastname +"</b>.</p>";
        else
            return "<p>Employee not created</p>";
    }


    @GET
    @Path("/read")
    @Produces(MediaType.TEXT_HTML)
    public String readEmployee(@QueryParam("id") Integer id){
        Employee employee = employeeDao.read(id);

        if (employee!=null)
            return "<p>The employee name is <b> " +
                    employee.getFirstname() + " " +
                    employee.getLastname() +"</b>.</p>";
        else
            return "<p>Employee not read. Make sure to type a valid employee id</p>";
    }


    @PUT
    @Path("/update")
    @Produces(MediaType.TEXT_HTML)
    public String updateEmployee(@QueryParam("id") Integer id,
                                 @QueryParam("firstname") String firstname,
                                 @QueryParam("lastname") String lastname,
                                 @QueryParam("occupation") String occupation){
        Employee employeeToUpdate = employeeDao.read(id);
            employeeToUpdate.setFirstname(firstname);
            employeeToUpdate.setLastname(lastname);
            employeeToUpdate.setOccupation(occupation);
        employeeDao.update(employeeToUpdate);
        if (!employeeToUpdate.getFirstname().equals(firstname))
            return "<p>The employee updated parameters are:</p> " +
                    "<p>firstname: " + employeeDao.read(id).getFirstname() + " </p>" +
                    "<p>lastname: " + employeeDao.read(id).getLastname() + " </p>" ;
        else
            return "<p>Employee not updated. Make sure to type a valid employee id</p>";
    }


    @DELETE
    @Path("/delete")
    @Produces(MediaType.TEXT_HTML)
    public String deleteEmployee(@QueryParam("id") Integer id){
        Employee employeeToDelete = employeeDao.read(id);
        employeeDao.delete(employeeToDelete);

        if (employeeDao.read(id) == null)
            return "<p>The employee has been deleted properly</p>";
        else
            return "<p>Employee not deleted. Make sure to type a valid employee id</p>";
    }

}
