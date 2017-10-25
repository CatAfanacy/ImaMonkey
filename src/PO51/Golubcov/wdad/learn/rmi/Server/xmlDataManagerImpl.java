package PO51.Golubcov.wdad.learn.rmi.Server;

import PO51.Golubcov.wdad.utils.Departament;
import PO51.Golubcov.wdad.utils.Employee;
import PO51.Golubcov.wdad.utils.JobTitle;
import org.w3c.dom.*;

import java.io.File;
import java.rmi.server.UnicastRemoteObject;


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
        doc = builder.parse(new File("\\PO51\\Golubcov\\wdad\\learn\\xml\\goodExample.xml"));
    }

    @Override
    public int salaryAverage() {
    }

    @Override
    public int salaryAverage(String departmentName) {

    }

    @Override
    public void setJobTitle(Employee employee, JobTitle newJobTitle) throws TransformException {

    }

    @Override
    public void setSalary(Employee employee, int newSalary) throws TransformException {

    }

    @Override
    public void fireEmployee(Employee employee) throws TransformException {

    }

    @Override
    public void add(Departament department) throws TransformException  {

    }

    private void saveTransformXML() throws TransformException {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src\\PO51\\Golubcov\\wdad\\learn\\xml\\goodExample.xml"));
            transformer.transform(source, result);
        } catch (TransformerException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
