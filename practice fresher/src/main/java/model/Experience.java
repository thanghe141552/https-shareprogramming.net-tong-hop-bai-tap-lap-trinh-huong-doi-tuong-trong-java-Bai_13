package model;

import java.util.Date;
public class Experience extends Employee{
    private Date expInYear;
    private String proSkill;

    public Experience(Long id, String fullName, Date birthDay, String phone, String email, Employee_type employee_type) {
        super(id, fullName, birthDay, phone, email, employee_type);
    }
    public  Experience(){

    }

    public Date getExpInYear() {
        return expInYear;
    }

    public void setExpInYear(Date expInYear) {
        this.expInYear = expInYear;
    }

    public String getProSkill() {
        return proSkill;
    }

    public void setProSkill(String proSkill) {
        this.proSkill = proSkill;
    }

    @Override
    public String showInfo() {
        return "Experience{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", birthDay=" + birthDay +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", employeeType=" + employeeType + '\'' +
                "expInYear='" + expInYear + '\'' +
                ", proSkill='" + proSkill + '\'' +
                '}';
    }

}
