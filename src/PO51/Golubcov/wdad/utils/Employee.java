package PO51.Golubcov.wdad.utils;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable{

    private String firstName;
    private String secondName;
    private Date hireDate;
    private int salary;
    private JobTitle jobTitle;

    public Employee(String firstName, String secondName, Date hireDate, int salary, JobTitle jobTitle){
        this.firstName  =   firstName;
        this.secondName =   secondName;
        this.hireDate   =   hireDate;
        this.salary     =   salary;
        this.jobTitle   =   jobTitle;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public Date getHiredate() {
        return hireDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }
}
