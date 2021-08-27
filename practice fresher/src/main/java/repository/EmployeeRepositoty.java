package repository;

import model.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoty {
    List<Employee> employees;
    public static String fileName = "C:\\Users\\ADMIN\\Desktop\\employees.txt";
    public EmployeeRepositoty() {
        this.employees = new ArrayList<>();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void saveEmployeesToFile() {
        ObjectOutputStream objectOutputStream = null;
        try{
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(EmployeeRepositoty.fileName));
            for (int i = 0; i < employees.size(); i++) {
                objectOutputStream.writeObject(employees.get(i));
            }
            System.out.println("Done saving emps");
        }catch (IOException e){
           e.printStackTrace();
        }finally {
            if(objectOutputStream != null){
                try{
                    objectOutputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public void takeEmployeesFromFile(){
        ObjectInputStream objectInputStream = null;
        try{
            boolean outOfObject = false;
            objectInputStream = new ObjectInputStream(new FileInputStream(EmployeeRepositoty.fileName));
            List<Employee> employeesTemp= new ArrayList<>();
            while(!outOfObject){
                Employee employeeTemps = null;
                try{
                    employeeTemps = (Employee) objectInputStream.readObject();
                    this.employees.add(employeeTemps);
                    employeesTemp.add(employeeTemps);
                }catch (IOException e){
                    if(employeesTemp.size()==0){
                        System.out.println("Not have any emps");
                        outOfObject = true;
                    }else {
                        outOfObject = true;
                        System.out.println("Done taking all emps");
                    }
                }catch (ClassNotFoundException e){
                    e.printStackTrace();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(objectInputStream != null){
                try{
                    objectInputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
