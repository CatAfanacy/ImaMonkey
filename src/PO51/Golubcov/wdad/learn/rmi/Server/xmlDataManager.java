package PO51.Golubcov.wdad.learn.rmi.Server;

import PO51.Golubcov.wdad.utils.Departament;
import PO51.Golubcov.wdad.utils.Employee;
import PO51.Golubcov.wdad.utils.JobTitle;

import javax.xml.crypto.dsig.TransformException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface xmlDataManager extends Remote {
    int salaryAverage() throws RemoteException,TransformException; // возвращает среднюю заработную плату сотрудников организации.
    int salaryAverage(String departmentName) throws RemoteException,TransformException; // возвращает среднюю заработную плату сотрудников заданного департамента.
    void setJobTitle(Employee employee, JobTitle newJobTitle) throws RemoteException,TransformException; //изменяет должность сотрудника
    void setSalary(Employee employee, int newSalary) throws RemoteException,TransformException; // изменяет размер заработной платы сотрудника.
    void fireEmployee(Employee employee) throws RemoteException,TransformException; // удаляющий информацию о сотруднике.
    void add(Departament department) throws RemoteException,TransformException; // добавляющий информацию о департаменте. Если такой департамент уже есть, добавляет (или заменяет) в него информацию по сотрудникам.
}