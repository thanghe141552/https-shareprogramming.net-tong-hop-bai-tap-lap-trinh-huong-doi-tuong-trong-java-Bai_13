package com.example.demo;

import repository.EmployeeRepositoty;
import service.EmployeeService;

public class DemoApplication {



    public static void main(String[] args) {

        EmployeeRepositoty employeeRepositoty = new EmployeeRepositoty();
        EmployeeService employeeService = new EmployeeService(employeeRepositoty);
    }
}
