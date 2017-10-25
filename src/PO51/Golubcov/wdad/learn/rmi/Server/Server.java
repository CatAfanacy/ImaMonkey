package PO51.Golubcov.wdad.learn.rmi.Server;


import PO51.Golubcov.wdad.data.managers.PreferencesManager;
import PO51.Golubcov.wdad.learn.xml.xmlTask;
import PO51.Golubcov.wdad.utils.PreferencesConstantManager;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;

public class Server {

    private static final int EXECUTOR_PORT = 49100;

    public static void main(String[] args) throws Exception{

        PreferencesManager prefMan = PreferencesManager.getInstance();
        int regPort = Integer.parseInt(prefMan.getProperty(PreferencesConstantManager.REGISTRYPORT));
       String isCreateReg = prefMan.getProperty(PreferencesConstantManager.CREATEREGISTRY);

        String securityPolicyPath = prefMan.getProperty(PreferencesConstantManager.POLICYPATH);
        System.setProperty("java.rmi.server.logCalls", "true");
        System.setProperty("java.security.policy", securityPolicyPath);
        System.setSecurityManager(new SecurityManager());

        Registry reg = null;
        try{
            if (isCreateReg.equals("yes")){
                reg = LocateRegistry.createRegistry(regPort);
            }else reg = LocateRegistry.getRegistry(regPort);
        }catch (RemoteException ex){
            System.err.println("this isn`t a local object");
            ex.printStackTrace();
        }
        try{
            System.out.println("Server running");
            xmlDataManagerImpl xdmi = new xmlDataManagerImpl();

            UnicastRemoteObject.exportObject(xdmi, EXECUTOR_PORT);
            reg.rebind("XmlDataManager",xdmi);
            prefMan.addBindedObject("XmlDataManager", xmlDataManager.class.getCanonicalName());
            prefMan.saveXML();
            System.out.println("Actions successfully");
        }catch (Exception ex){
            System.err.println("Because of an error, the server cann`t work");
            ex.printStackTrace();
        }

    }
}