package com.test;

import com.dao.dao.employee.EmployeeDaoImpl;
import com.dao.dao.interfaces.employee.EmployeeDao;
import com.dao.entities.employee.Employee;

/**
 * Created by olivier on 20/11/15.
 */
public class DaoTest {

    public static void main (String[] args){
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        Employee employee = new Employee();
            employee.setFirstname("hello");
            employee.setLastname("world");
        employeeDao.create(employee);
        Employee employeeRead = employeeDao.read(1);
        System.out.println(employeeRead.getFirstname());
        }
    }