package PO51.Golubcov.wdad.learn.rmi.Server;


import PO51.Golubcov.wdad.data.managers.PreferencesManager;
import PO51.Golubcov.wdad.utils.PreferencesConstantManager;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;

public class Server {

    public static void main(String[] args) throws Exception{

        PreferencesManager prefMan = PreferencesManager.getInstance();
        int regPort = Integer.parseInt(prefMan.getProperty(PreferencesConstantManager.REGISTRYPORT));
        String refAdd = prefMan.getProperty(PreferencesConstantManager.REGISTRYADDRESS);
        String isCreateReg = prefMan.getProperty(PreferencesConstantManager.CREATEREGISTRY);
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
            xmlDataManagerImpl xdmi = xmlDataManagerImpl();
            prefMan.addBindedObject("XmlDataManager", xmlDataManager.class.getCanonicalName());
            System.out.println("Server started");
        }

    }
}