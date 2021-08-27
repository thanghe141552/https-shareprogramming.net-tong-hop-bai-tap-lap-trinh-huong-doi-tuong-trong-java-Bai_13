package service;

import repository.EmployeeRepositoty;
import customizeException.BirthDayException;
import customizeException.EmailException;
import customizeException.FullNameException;
import customizeException.PhoneException;
import model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeService {
    EmployeeRepositoty employeeRepositoty;

    public EmployeeService(EmployeeRepositoty employeeRepositoty) {
        this.employeeRepositoty = employeeRepositoty;
        customizeEmployeeList();
    }

    //Menu operation with employee
    public int menu() {
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
            System.out.println("6. Take employees from the file");
            System.out.println("7. Save employees from the file");
            System.out.println("Enter your choice: ");
            pick = Integer.parseInt(scanner.next());
            if (pick >= 1 && pick <= 7) {
                menuSyntax = true;
            }
        }
        return pick;
    }

    //Operation with employee for each case user choose
    public void customizeEmployeeList() {
        while (true) {
            System.out.println("Have total " + employeeRepositoty.getEmployees().size() + " employees");
            int pick = menu();
            switch (pick) {
                case 1:
                    addEmps();
                    break;
                case 2:
                    editEmployee();
                    break;
                case 3:
                    deleteEmployee();
                    break;
                case 4:
                    showEmployeeInfo();
                    break;
                case 5:
                    showAllEmployeeInfo();
                    break;
                case 6:
                    employeeRepositoty.takeEmployeesFromFile();
                    break;
                case 7:
                    employeeRepositoty.saveEmployeesToFile();
                    break;
            }
        }
    }

    //Add employee
    public void addEmps() {
        boolean isContinue = true;
        System.out.println("---------INSERT EMPLOYEE---------");
        while (isContinue) {
            String fullName, email, phone, userType = null;
            Date birthDay = null;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Have total " + employeeRepositoty.getEmployees().size() + " employees");
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
            employeeRepositoty.getEmployees().add(employeeTemp);
            Experience.employee_count++;
            isContinue = continueLoop("Employees");
        }
    }


    //Add certificate
    public List<Certificate> addCertificate() {
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

    //show all info of employees
    public void showAllEmployeeInfo() {
        for (int i = 0; i < employeeRepositoty.getEmployees().size(); i++) {
            if (employeeRepositoty.getEmployees().get(i) instanceof Experience) {
                System.out.println(Employee.downCastingExperience(employeeRepositoty.getEmployees().get(i)).showInfo());
            } else if (employeeRepositoty.getEmployees().get(i) instanceof Fresher) {
                System.out.println(Employee.downCastingFresher(employeeRepositoty.getEmployees().get(i)).showInfo());
            } else {
                System.out.println(Employee.downCastingIntern(employeeRepositoty.getEmployees().get(i)).showInfo());
            }
        }
    }

    //show info of employee in each type
    public void showEmployeeInfo() {
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
            for (int i = 0; i < employeeRepositoty.getEmployees().size(); i++) {
                if (employeeRepositoty.getEmployees().get(i) instanceof Experience) {
                    System.out.println(Employee.downCastingExperience(employeeRepositoty.getEmployees().get(i)).showInfo());
                }
            }
        } else if (userType.equalsIgnoreCase("Fresher")) {
            for (int i = 0; i < employeeRepositoty.getEmployees().size(); i++) {
                if (employeeRepositoty.getEmployees().get(i) instanceof Fresher) {
                    System.out.println(Employee.downCastingFresher(employeeRepositoty.getEmployees().get(i)).showInfo());
                }
            }
        } else {
            for (int i = 0; i < employeeRepositoty.getEmployees().size(); i++) {
                if (employeeRepositoty.getEmployees().get(i) instanceof Intern) {
                    System.out.println(Employee.downCastingIntern(employeeRepositoty.getEmployees().get(i)).showInfo());
                }
            }
        }
    }


    public void editEmployee() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert id employee edit: ");
        Long idEmp = Long.parseLong(scanner.next());
        for (int i = 0; i < employeeRepositoty.getEmployees().size(); i++) {
            if (employeeRepositoty.getEmployees().get(i).getId().equals(idEmp)) {
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
                    Experience experienceTemp = new Experience(employeeRepositoty.getEmployees().get(i).getId(), fullName, birthDay, phone, email, Employee.Employee_type.Experience);
                    Date expInYear = getDate("expInYear");
                    experienceTemp.setExpInYear(expInYear);
                    System.out.println("Insert proSkill: ");
                    String proSkill = scanner.next();
                    experienceTemp.setProSkill(proSkill);
                    employeeTemp = (Employee) experienceTemp;
                } else if (userType.equalsIgnoreCase("Fresher")) {
                    Fresher fresherTemp = new Fresher(employeeRepositoty.getEmployees().get(i).getId(), fullName, birthDay, phone, email, Employee.Employee_type.Fresher);
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
                    Intern internTemp = new Intern(employeeRepositoty.getEmployees().get(i).getId(), fullName, birthDay, phone, email, Employee.Employee_type.Intern);
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
                employeeRepositoty.getEmployees().set(i, employeeTemp);
            }
        }
        System.out.println("Id not include");
    }

    public void deleteEmployee() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert id employee remove: ");
        Long idEmp = Long.parseLong(scanner.next());
        for (int i = 0; i < employeeRepositoty.getEmployees().size(); i++) {
            if (employeeRepositoty.getEmployees().get(i).getId().equals(idEmp)) {
                employeeRepositoty.getEmployees().remove(i);
            }
        }
        System.out.println("Id not include");
    }

    //Make user input again if date not in form
    public Date getDate(String s) {
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
    public Date inputDate(String s) throws BirthDayException {
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
    public String inputEmail() throws EmailException {
        System.out.println("Insert email (example: abc@abc.com: ");
        String email = new Scanner(System.in).next();
        String regex = "^[A-Za-z0-9+_.-]+@(.+)+.com$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new EmailException("Wrong email type");
        }
        return email;
    }

    public String getEmail() {
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
    public String getFullName() {
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

    public String inputFullName() throws FullNameException {
        System.out.println("Insert full name (no number): ");
        String fullName = new Scanner(System.in).next();
        String regex = "^[A-Za-z ]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fullName);
        if (!matcher.matches()) {
            throw new FullNameException("Wrong name type");
        }
        return fullName;
    }

    //Make user input again if phone not in form
    public String getPhone() {
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

    public String inputPhone() throws PhoneException {
        System.out.println("Insert phone (10 number start with 09xxx) : ");
        String phone = new Scanner(System.in).next();
        String regex = "^09[0-9]{8}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        if (!matcher.matches()) {
            throw new PhoneException("Wrong phone type");
        }
        return phone;
    }

    //Provide if the loop is continued or not
    public boolean continueLoop(String s) {
        boolean continueSyntax = true;
        boolean isContinue = true;
        Scanner scanner = new Scanner(System.in);
        while (continueSyntax) {
            System.out.println("Continue insert " + s + " ? y:n : ");
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
}
