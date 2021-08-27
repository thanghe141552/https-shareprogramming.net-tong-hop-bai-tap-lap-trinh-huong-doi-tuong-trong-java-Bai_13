package model;

import java.util.Date;

public class Intern extends Employee{

    private String majors;
    private String semester;
    private String universityName;
    public Intern(Long id, String fullName, Date birthDay, String phone, String email, Employee_type employeeType) {
        super(id, fullName, birthDay, phone, email, employeeType);
    }
    public Intern(){

    }

    public String getMajors() {
        return majors;
    }

    public void setMajors(String majors) {
        this.majors = majors;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    @Override
    public String showInfo() {
        String certificateListToString = "";
        for (int i = 0; i < certificateList.size(); i++) {
            certificateListToString+= certificateList.get(i).toString();
        }
        return "Intern{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", birthDay=" + birthDay +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", employeeType=" + employeeType + '\'' +
                "majors='" + majors + '\'' +
                ", semester='" + semester + '\'' +
                ", universityName='" + universityName + '\'' +
                certificateListToString+
                '}';
    }

}
