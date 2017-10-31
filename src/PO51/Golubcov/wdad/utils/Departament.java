package PO51.Golubcov.wdad.utils;


import java.io.Serializable;
import java.util.List;

public class Departament implements Serializable {
    private String name;
    private List<Employee> employeeList;

    public Departament(String name, List<Employee> employeeList ){
        this.name           =   name;
        this.employeeList   =   employeeList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
