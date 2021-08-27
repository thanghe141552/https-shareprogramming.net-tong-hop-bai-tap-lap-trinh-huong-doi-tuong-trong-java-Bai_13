package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Employee implements Serializable {
     private static final long serialVersionUID = 611200003082000L;
     Long id;
     String fullName;
     Date birthDay;
     String phone;
     String email;

    public enum Employee_type {Experience, Fresher, Intern}
    Employee_type employeeType;
    List<Certificate> certificateList = new ArrayList<>();
    public static Long employee_count = 0L;



    public Employee() {

    }

    public Employee(Long id, String fullName, Date birthDay, String phone, String email, Employee_type employeeType) {
        this.id = id;
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.phone = phone;
        this.email = email;
        this.employeeType = employeeType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Employee_type getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(Employee_type employeeType) {
        this.employeeType = employeeType;
    }

    public List<Certificate> getCertificateList() {
        return certificateList;
    }

    public void setCertificateList(List<Certificate> certificateList) {
        this.certificateList = certificateList;
    }

    public abstract String showInfo() ;



    public static Fresher downCastingFresher(Employee e) {
        Fresher fresher = (Fresher) e;
        return fresher;
    }

    public static Experience downCastingExperience(Employee e) {
        Experience experience = (Experience) e;
        return experience;
    }

    public static Intern downCastingIntern(Employee e) {
        Intern intern = (Intern) e;
        return intern;
    }
}
