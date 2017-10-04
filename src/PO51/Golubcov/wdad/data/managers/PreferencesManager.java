package PO51.Golubcov.wdad.data.managers;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class PreferencesManager {

    private static volatile PreferencesManager instance;
    private final Document doc;
    private final String pathConfig = "src\\PO51\\Golubcov\\wdad\\resources\\configuration\\appconfig.xml";


    public static PreferencesManager getInstance() throws Exception {
        if (instance == null)
            synchronized (PreferencesManager.class){
                if (instance == null)
                    instance = new PreferencesManager();
            }
            return instance;
    }

    private PreferencesManager() throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        doc = db.parse(new File(pathConfig));
    }

    createregistry
    registryaddress
    registryport

 policypath
 usecodebaseonly
    classprovider


    public String getCreaterRegistry() {
        String registry;
                
        return registry;
    }

    public String getCreaterRegistry() {
        return registry;
    }
    public String getCreaterRegistry() {
        return registry;
    }
    public String getCreaterRegistry() {
        return registry;
    }
    public String getCreaterRegistry() {
        return registry;
    }
    public String getCreaterRegistry() {
        return registry;
    }


    public String setCreaterRegistry() {
        return registry;
    }

    public String setCreaterRegistry() {
        return registry;
    }
    public String setCreaterRegistry() {
        return registry;
    }
    public String setCreaterRegistry() {
        return registry;
    }
    public String setCreaterRegistry() {
        return registry;
    }
    public String setCreaterRegistry() {
        return registry;
    }



}
