package PO51.Golubcov.wdad.learn.rmi.Client;

import PO51.Golubcov.wdad.data.managers.PreferencesManager;
import PO51.Golubcov.wdad.learn.rmi.Server.xmlDataManager;
import PO51.Golubcov.wdad.utils.Departament;
import PO51.Golubcov.wdad.utils.Employee;
import PO51.Golubcov.wdad.utils.JobTitle;
import PO51.Golubcov.wdad.utils.PreferencesConstantManager;
import org.w3c.dom.Element;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Client {
    public static void main(String[] args) throws Exception {

        PreferencesManager prefMan = PreferencesManager.getInstance();
        String policyPath = prefMan.getProperty(PreferencesConstantManager.POLICYPATH);
        int regPort = Integer.parseInt(prefMan.getProperty(PreferencesConstantManager.REGISTRYPORT));
        String regAddress = prefMan.getProperty(PreferencesConstantManager.REGISTRYADDRESS);
        String exName = prefMan.getExecutorName();

        System.setProperty("java.security.policy", policyPath);
        System.setSecurityManager(new SecurityManager());

        Registry registry = null;

        try {
            registry = LocateRegistry.getRegistry(regAddress,regPort);
        } catch (RemoteException re) {
            System.err.println("cann`t find the registry");
            re.printStackTrace();
        }

        try {
            xmlDataManager XDM=(xmlDataManager)registry.lookup(exName);

            System.out.println(XDM.salaryAverage());
            System.out.println(XDM.salaryAverage("Worker"));

            List<Employee> employeeList=new ArrayList<>();
            employeeList.add(new Employee("Paul","Worker",new Date(2015,11,6),13700, JobTitle.assistant));
            employeeList.add(new Employee("Jora","Arbuzov",new Date(2015,01,01),13000, JobTitle.engineer));
            employeeList.add(new Employee("Alexsandr","Beardedman",new Date(2015,12,10),1500, JobTitle.manager));
            employeeList.add(new Employee("Nikola","Canonov",new Date(2015,7,25),25415, JobTitle.head));
            Departament department=new Departament("ClearnManager",employeeList);
            XDM.add(department);

            Employee testEmployee = new Employee("Samuil", "Vakoulinko",new Date(2007,10,15),30000,JobTitle.manager);
            XDM.setJobTitle(testEmployee, JobTitle.engineer);
            XDM.setSalary(testEmployee,35000);

            Employee fireEmployee = new Employee("Maria","Vakoulinko", new Date(2007,10,02),75000, JobTitle.secretary );
            XDM.fireEmployee(fireEmployee);
        }catch (Exception ex){
            System.out.println("Oups");
            ex.printStackTrace();
        }
    }
}
