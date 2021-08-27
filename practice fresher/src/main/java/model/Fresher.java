package model;

import java.util.Date;

public class Fresher extends Employee{
    private Date graduationDate;
    private String graduationRank;
    private String education;
    public Fresher(Long id, String fullName, Date birthDay, String phone, String email, Employee_type employee_type) {
        super(id, fullName, birthDay, phone, email, employee_type);
    }
    public  Fresher(){

    }

    public Date getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(Date graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getGraduationRank() {
        return graduationRank;
    }

    public void setGraduationRank(String graduationRank) {
        this.graduationRank = graduationRank;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Override
    public String showInfo() {
        String certificateListToString = "";
        for (int i = 0; i < certificateList.size(); i++) {
            certificateListToString+= certificateList.get(i).toString();
        }
        return "Fresher{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", birthDay=" + birthDay +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", employeeType=" + employeeType + '\'' +
                "graduationDate='" + graduationDate + '\'' +
                ", graduationRank='" + graduationRank + '\'' +
                ", education='" + education + '\'' +
                certificateListToString+
                '}';
    }
}
