package com.example.demo;

import Repository.EmployeeRepositoty;
import Service.EmployeeService;
import customizeException.BirthDayException;
import customizeException.EmailException;
import customizeException.FullNameException;
import customizeException.PhoneException;
import model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DemoApplication {



    public static void main(String[] args) {

        EmployeeRepositoty employeeRepositoty = new EmployeeRepositoty();
        EmployeeService employeeService = new EmployeeService(employeeRepositoty);
    }
}
