package com.test;

import com.dao.dao.EmployeeDaoImpl;
import com.dao.dao.interfaces.EmployeeDao;
import com.dao.entities.Employee;

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