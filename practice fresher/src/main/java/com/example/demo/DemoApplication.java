package com.example.demo;

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

    //Make user input again if date not in form
    public static Date getDate(String s) {
        boolean checkDate = true;
        Date date = null;
        while (checkDate) {
            try {
                date = inputDate(s);
                checkDate = false;
            } catch (BirthDayException e) {
                System.out.println(e);
            }
        }
        return date;
    }
    //check form of date
    public static Date inputDate(String s) throws BirthDayException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setLenient(false); //correct the date
        Scanner scanner = new Scanner(System.in);
        Date date;
        try {
            System.out.println("Insert " + s + " (dd/MM/yyyy): ");
            date = simpleDateFormat.parse(scanner.next());
        } catch (ParseException e) {
            throw new BirthDayException("Date not in form");
        }
        return date;
    }
    //Make user input again if email not in form
    public static String inputEmail()throws EmailException{
        System.out.println("Insert email (example: abc@abc.com: ");
        String email = new Scanner(System.in).next();
        String regex = "^[A-Za-z0-9+_.-]+@(.+)+.com$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()){
            throw new EmailException("Wrong email type");
        }
        return email;
    }
    public static String getEmail(){
        boolean checkEmail = true;
        String email = null;
        while (checkEmail) {
            try {
                email = inputEmail();
                checkEmail = false;
            } catch (EmailException e) {
                System.out.println(e);
            }
        }
        return email;
    }
    //Make user input again if fullName not in form
    public static String getFullName(){
        boolean checkFullName = true;
        String fullName = null;
        while (checkFullName) {
            try {
                fullName = inputFullName();
                checkFullName = false;
            } catch (FullNameException e) {
                System.out.println(e);
            }
        }
        return fullName;
    }

    public static String inputFullName()throws FullNameException {
        System.out.println("Insert full name (no number): ");
        String fullName = new Scanner(System.in).next();
        String regex = "^[A-Za-z ]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fullName);
        if(!matcher.matches()){
            throw new FullNameException("Wrong name type");
        }
        return fullName;
    }

    //Make user input again if phone not in form
    public static String getPhone(){
        boolean checkPhone = true;
        String phone = null;
        while (checkPhone) {
            try {
                phone = inputPhone();
                checkPhone = false;
            } catch (PhoneException e) {
                System.out.println(e);
            }
        }
        return phone;
    }

    public static String inputPhone()throws PhoneException {
        System.out.println("Insert phone (10 number start with 09xxx) : ");
        String phone = new Scanner(System.in).next();
        String regex = "^09[0-9]{8}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        if(!matcher.matches()){
            throw new PhoneException("Wrong phone type");
        }
        return phone;
    }


    public static boolean continueLoop(String s) {
        boolean continueSyntax = true;
        boolean isContinue = true;
        Scanner scanner = new Scanner(System.in);
        while (continueSyntax) {
            System.out.println("Continue insert "+s+" ? y:n : ");
            String end = scanner.next();
            if (end.equalsIgnoreCase("y")) {
                continueSyntax = false;
            } else if (end.equalsIgnoreCase("n")) {
                continueSyntax = false;
                isContinue = false;
            }
        }
        return isContinue;
    }

    public static List<Certificate> addCertificate() {
        List<Certificate> certificateList = new ArrayList<>();
        boolean isContinue = true;
        System.out.println("---------INSERT CERTIFICATE OF EMPLOYEE---------");
        while (isContinue) {
            String certificateName, certificateRank;
            Date certificatedDate;
            Long certificatedID = 0L;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Insert certificateName: ");
            certificateName = scanner.next();
            System.out.println("Insert certificateRank: ");
            certificateRank = scanner.next();
            certificatedDate = getDate("certificatedDate");
            Certificate certificateTemp = new Certificate(certificatedID, certificateName, certificateRank, certificatedDate);
            certificateList.add(certificateTemp);
            isContinue = continueLoop("Certificate");
        }
        return certificateList;
    }

    public static List<Employee> addEmps(List<Employee> employees) {
        boolean isContinue = true;
        System.out.println("---------INSERT EMPLOYEE---------");
        while (isContinue) {
            String fullName, email, phone, userType = null;
            Date birthDay = null;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Have total "+employees.size()+" employees");
            fullName = getFullName();
            email = getEmail();
            phone = getPhone();
            birthDay = getDate("birthDay");
            boolean checkTypeCorrect = false;
            while (!checkTypeCorrect) {
                System.out.println("Insert type of user (Experience,Fresher,Intern): ");
                String userTypeTemp = scanner.next();
                for (Employee.Employee_type a : Employee.Employee_type.values()) {
                    if (userTypeTemp.equalsIgnoreCase(a.name())) {
                        userType = userTypeTemp;
                        checkTypeCorrect = true;
                    }
                }
            }
            Employee employeeTemp;
            if (userType.equalsIgnoreCase("Experience")) {
                Experience experienceTemp = new Experience(Experience.employee_count, fullName, birthDay, phone, email, Employee.Employee_type.Experience);
                Date expInYear = getDate("expInYear");
                experienceTemp.setExpInYear(expInYear);
                System.out.println("Insert proSkill: ");
                String proSkill = scanner.next();
                experienceTemp.setProSkill(proSkill);
                employeeTemp = (Employee) experienceTemp;
            } else if (userType.equalsIgnoreCase("Fresher")) {
                Fresher fresherTemp = new Fresher(Experience.employee_count, fullName, birthDay, phone, email, Employee.Employee_type.Fresher);
                Date graduationDate = getDate("graduationDate");
                fresherTemp.setGraduationDate(graduationDate);
                System.out.println("Insert graduationRank: ");
                String graduationRank = scanner.next();
                fresherTemp.setGraduationRank(graduationRank);
                System.out.println("Insert education: ");
                String education = scanner.next();
                fresherTemp.setEducation(education);
                employeeTemp = (Employee) fresherTemp;
            } else {
                Intern internTemp = new Intern(Experience.employee_count, fullName, birthDay, phone, email, Employee.Employee_type.Intern);
                System.out.println("Insert majors: ");
                String majors = scanner.next();
                internTemp.setMajors(majors);
                System.out.println("Insert semester: ");
                String semester = scanner.next();
                internTemp.setSemester(semester);
                System.out.println("Insert universityName: ");
                String universityName = scanner.next();
                internTemp.setUniversityName(universityName);
                employeeTemp = (Employee) internTemp;
            }
            employeeTemp.setCertificateList(addCertificate());
            employees.add(employeeTemp);
            Experience.employee_count++;
            isContinue = continueLoop("Employees");
        }
        return employees;
    }

    public  static void showAllEmployeeInfo(List<Employee> employees){
        for (int i = 0; i < employees.size(); i++) {
            employees.get(i).showInfo();
        }
    }

    //show info of employee in each type
    public static void showEmployeeInfo(List<Employee> employees) {
        Scanner scanner = new Scanner(System.in);
        boolean checkTypeCorrect = false;
        String userType = null;
        while (!checkTypeCorrect) {
            System.out.println("Insert type of user you want to see (Experience,Fresher,Intern): ");
            String userTypeTemp = scanner.next();
            for (Employee.Employee_type a : Employee.Employee_type.values()) {
                if (userTypeTemp.equalsIgnoreCase(a.name())) {
                    userType = userTypeTemp;
                    checkTypeCorrect = true;
                }
            }
        }
        if (userType.equalsIgnoreCase("Experience")) {
            for (int i = 0; i < employees.size(); i++) {
                if (employees.get(i) instanceof Experience) {
                    System.out.println(Employee.downCastingExperience(employees.get(i)).showInfo());
                }
            }
        } else if (userType.equalsIgnoreCase("Fresher")) {
            for (int i = 0; i < employees.size(); i++) {
                if (employees.get(i) instanceof Fresher) {
                    System.out.println(Employee.downCastingFresher(employees.get(i)).showInfo());
                }
            }
        } else {
            for (int i = 0; i < employees.size(); i++) {
                if (employees.get(i) instanceof Intern) {
                    System.out.println(Employee.downCastingIntern(employees.get(i)).showInfo());
                }
            }
        }
    }

    public static int menu() {
        boolean menuSyntax = false;
        Scanner scanner = new Scanner(System.in);
        int pick = 0;
        while (!menuSyntax) {
            System.out.println("Pick 3 option : ");
            System.out.println("1. Insert more employee");
            System.out.println("2. Edit employee");
            System.out.println("3. Delete employee");
            System.out.println("4. Show info specific employee");
            System.out.println("5. Show all info employee");
            pick = Integer.parseInt(scanner.next());
            if (pick >= 1 && pick <= 5) {
                menuSyntax = true;
            }
        }
        return pick;
    }


    public static List<Employee> editEmployee(List<Employee> employees) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert id employee edit: ");
        Long idEmp = Long.parseLong(scanner.next());
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId().equals(idEmp)) {
                String fullName, email, phone, userType = null;
                Date birthDay = null;
                System.out.println("Insert fullName: ");
                fullName = scanner.next();
                System.out.println("Insert email: ");
                email = scanner.next();
                System.out.println("Insert phone: ");
                phone = scanner.next();
                birthDay = getDate("birthDay");
                boolean checkTypeCorrect = false;
                while (!checkTypeCorrect) {
                    System.out.println("Insert type of user (Experience,Fresher,Intern): ");
                    String userTypeTemp = scanner.next();
                    for (Employee.Employee_type a : Employee.Employee_type.values()) {
                        if (userTypeTemp.equalsIgnoreCase(a.name())) {
                            userType = userTypeTemp;
                            checkTypeCorrect = true;
                        }
                    }
                }
                Employee employeeTemp = null;
                if (userType.equalsIgnoreCase("Experience")) {
                    Experience experienceTemp = new Experience(employees.get(i).getId(), fullName, birthDay, phone, email, Employee.Employee_type.Experience);
                    Date expInYear = getDate("expInYear");
                    experienceTemp.setExpInYear(expInYear);
                    System.out.println("Insert proSkill: ");
                    String proSkill = scanner.next();
                    experienceTemp.setProSkill(proSkill);
                    employeeTemp = (Employee) experienceTemp;
                } else if (userType.equalsIgnoreCase("Fresher")) {
                    Fresher fresherTemp = new Fresher(employees.get(i).getId(), fullName, birthDay, phone, email, Employee.Employee_type.Fresher);
                    Date graduationDate = getDate("graduationDate");
                    fresherTemp.setGraduationDate(graduationDate);
                    System.out.println("Insert graduationRank: ");
                    String graduationRank = scanner.next();
                    fresherTemp.setGraduationRank(graduationRank);
                    System.out.println("Insert education: ");
                    String education = scanner.next();
                    fresherTemp.setEducation(education);
                    employeeTemp = (Employee) fresherTemp;
                } else {
                    Intern internTemp = new Intern(employees.get(i).getId(), fullName, birthDay, phone, email, Employee.Employee_type.Intern);
                    System.out.println("Insert majors: ");
                    String majors = scanner.next();
                    internTemp.setMajors(majors);
                    System.out.println("Insert semester: ");
                    String semester = scanner.next();
                    internTemp.setSemester(semester);
                    System.out.println("Insert universityName: ");
                    String universityName = scanner.next();
                    internTemp.setUniversityName(universityName);
                    employeeTemp = (Employee) internTemp;
                }
                employeeTemp.setCertificateList(addCertificate());
                employees.set(i, employeeTemp);
                return employees;
            }
        }
        System.out.println("Id not include");
        return employees;
    }

    public static List<Employee> deleteEmployee(List<Employee> employees) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert id employee remove: ");
        Long idEmp = Long.parseLong(scanner.next());
        for (int i = 0; i < employees.size(); i++) {
            if(employees.get(i).getId().equals(idEmp)){
                employees.remove(i);
                return employees;
            }
        }
        System.out.println("Id not include");
        return employees;
    }


    public static void customizeEmployeeList(List<Employee> employees) {
        while (true) {
            System.out.println();
            System.out.println("Have total "+employees.size()+" employees");
            int pick = menu();
            switch (pick) {
                case 1:
                    employees = addEmps(employees);
                    break;
                case 2:
                    employees = editEmployee(employees);
                    break;
                case 3:
                    employees = deleteEmployee(employees);
                    break;
                case 4:
                    showEmployeeInfo(employees);
                    break;
                case 5:
                    showAllEmployeeInfo(employees);
                    break;
            }
        }
    }

    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();
        addEmps(employees);
        customizeEmployeeList(employees);

    }
}
