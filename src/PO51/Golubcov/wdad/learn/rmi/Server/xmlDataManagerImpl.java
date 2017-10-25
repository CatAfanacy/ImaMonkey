package PO51.Golubcov.wdad.learn.rmi.Server;

import PO51.Golubcov.wdad.utils.Departament;
import PO51.Golubcov.wdad.utils.Employee;
import PO51.Golubcov.wdad.utils.JobTitle;
import org.w3c.dom.*;

import java.io.File;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;


import javax.xml.crypto.dsig.TransformException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class xmlDataManagerImpl extends UnicastRemoteObject implements xmlDataManager {
    private Document doc;

    public xmlDataManagerImpl() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        DocumentBuilder builder = factory.newDocumentBuilder();
        doc = builder.parse(new File("\\PO51\\Golubcov\\wdad\\learn\\rmi\\Server\\SeverXml.xml"));
    }

    @Override
    public int salaryAverage() {
        Element elNodeList;
        int salary =0;
        NodeList nodeList = doc.getElementsByTagName("employee");
        for (int i = 0; i< nodeList.getLength(); i++){
            elNodeList = (Element) nodeList.item(i);
            salary += Integer.parseInt(elNodeList.getElementsByTagName("salary").item(0).getTextContent());
        }
        return salary/nodeList.getLength();
    }

    @Override
    public int salaryAverage(String departmentName) throws TransformException {
        int salary=0;
        NodeList nodeList = doc.getElementsByTagName("department");
        NodeList listEmployees;
        Element elNodeList;
        for (int i = 0; i < nodeList.getLength() ; i++) {
            elNodeList = (Element) nodeList.item(i);
            if(elNodeList.getAttribute("name").equals(departmentName) ){
                listEmployees = elNodeList.getElementsByTagName("employee");
                for (int j = 0; j < listEmployees.getLength(); j++) {
                    elNodeList = (Element) listEmployees.item(i);
                    salary += Integer.parseInt(elNodeList.getElementsByTagName("salary").item(0).getTextContent());
                }
                return salary/listEmployees.getLength();
            }
        }
        return salary;
    }

    @Override
    public void setJobTitle(Employee employee, JobTitle newJobTitle) throws TransformException {
        NodeList nodeList = doc.getElementsByTagName("employee");
        Element elNodeList;
        for (int i = 0; i < nodeList.getLength() ; i++) {
            elNodeList = (Element) nodeList.item(i);
            if(elNodeList.getAttribute("firstname").equals(employee.getFirstName())&&
                    elNodeList.getAttribute("secondname").equals(employee.getSecondName())){
                elNodeList.setAttribute("jobtitle", newJobTitle.toString());
                saveTransformXML();
                break;
            }
        }
    }

    @Override
    public void setSalary(Employee employee, int newSalary) throws TransformException {
        NodeList nodeList = doc.getElementsByTagName("employee");
        Element elNodeList;
        for (int i = 0; i < nodeList.getLength() ; i++) {
            elNodeList = (Element) nodeList.item(i);
            if(elNodeList.getAttribute("firstname").equals(employee.getFirstName())&&
                    elNodeList.getAttribute("secondname").equals(employee.getSecondName())){
                elNodeList.setAttribute("jobtitle", String.valueOf(newSalary));
                saveTransformXML();
                break;
            }
        }
    }

    @Override
    public void fireEmployee(Employee employee) throws TransformException {
        NodeList nodeList = doc.getElementsByTagName("department");
        NodeList listEmployees;
        Element elNodeList;
        for (int i = 0; i < nodeList.getLength(); i++) {
            elNodeList = (Element) nodeList.item(i);
            listEmployees = elNodeList.getElementsByTagName("employee");
            for(int j = 0; j<listEmployees.getLength(); j++) {
                elNodeList = (Element) listEmployees.item(j);
                if ((elNodeList.getAttribute("firstname").equals(employee.getFirstName()) &&
                        elNodeList.getAttribute("secondname").equals(employee.getSecondName()))) {
                    nodeList.item(i).removeChild(elNodeList);
                    saveTransformXML();
                    break;
                }
            }
        }
    }

    @Override
    public void add(Departament department) throws TransformException  {
        Element elDepartament = doc.createElement("department");
        Element elEmployee;
        Element elTag;
        elDepartament.setAttribute("name", department.getName());
        List<Employee> employeeList = department.getEmployeeList();
        for(int i=0; i<employeeList.size();i++){
            elEmployee = doc.createElement("employee");
            elEmployee.setAttribute("firstname",employeeList.get(i).getFirstName());
            elEmployee.setAttribute("secondname",employeeList.get(i).getSecondName());
            elTag=doc.createElement("hiredate");
            Date date=employeeList.get(i).getHiredate();
            elTag.setTextContent(date.getDay()+"-"+date.getMonth()+"-"+date.getYear());
            elEmployee.appendChild(elTag);
            elTag=doc.createElement("salary");
            elTag.setTextContent(String.valueOf(employeeList.get(i).getSalary()));
            elEmployee.appendChild(elTag);
            elTag=doc.createElement("jobtitle");
            elTag.setAttribute("jobtitle",employeeList.get(i).getJobTitle().toString());
            elEmployee.appendChild(elTag);
            elDepartament.appendChild(elEmployee);
        }
    }

    private void saveTransformXML() throws TransformException {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src\\PO51\\Golubcov\\wdad\\learn\\rmi\\Server\\ServerXml.xml"));
            transformer.transform(source, result);
        } catch (TransformerException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
