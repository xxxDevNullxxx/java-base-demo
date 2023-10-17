package it.umana.demo.logic;


import it.umana.demo.spi.IDemoPlugin;
import lombok.extern.java.Log;


import java.util.HashMap;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;



/**
 *
 */
@Log
public class DemoPluginFactory {



    private static HashMap<String, Class<? extends IDemoPlugin>> availablePlugin;


//    @PostConstruct
    public void atStartup() {
        log.info("DemoPluginFactory Startup...");
        try {
            ServiceLoader<IDemoPlugin> loader = ServiceLoader.load(IDemoPlugin.class);
            availablePlugin = new HashMap<>();
            try {

                for (IDemoPlugin demoPlugin : loader) {
                    Class<? extends IDemoPlugin> aClass = demoPlugin.getClass();
                    String className = aClass.getName();
                    log.info("caricato plugin --> " + className);
                    availablePlugin.put(className, aClass);
                }
            } catch (ServiceConfigurationError serviceError) {
                serviceError.printStackTrace();
            }


        } catch (Exception e) {
            log.severe(e.toString());
        }
        log.info("DemoPluginFactory inizializzato correttamente.");
    }


    /**
     * @param className
     * @return
     */
    //@Lock(LockType.READ)
    public IDemoPlugin getDemoPluginByClassName(String className) {

        if (!availablePlugin.containsKey(className)) {
            throw new RuntimeException("Il plugin " + className + " non è diponibile");
        }

        IDemoPlugin demoPlugin = null;
        try {
            demoPlugin = availablePlugin.get(className).newInstance();
            if (demoPlugin == null) throw new Exception("Errore nella crazione dell'istanza del plugin " + className);
        } catch (Exception e) {
            log.severe(e.toString());
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return demoPlugin;
    }

}
