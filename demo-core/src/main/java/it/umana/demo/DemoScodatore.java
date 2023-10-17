package it.umana.demo;

import it.umana.demo.logic.Config;
import it.umana.demo.logic.DemoPluginFactory;
import it.umana.demo.logic.ScodatoreBO;
import lombok.extern.java.Log;

import static java.util.FormatProcessor.FMT;


@Log
public class DemoScodatore {

    private static final String HEADER = "         E                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             SN                                                                                                                                                                             N                                                                                                                                                                                                                                                                                                                                                                                     ";

    private static final String PAYLOAD = "20231017TESTDEMO";

    private static final String XBBU_BUFF = "0016";

    public static void main( String[] args )
    {

        log.info("Inizializzazione configurazione");
        Config config = new Config();

        log.info("Inizializzazione DemoPluginFactory");
        DemoPluginFactory pluginFactory = new DemoPluginFactory();
        pluginFactory.atStartup();

        log.info("Inizializzazione ScodatoreBO");
        ScodatoreBO scodatore = new ScodatoreBO(config,pluginFactory);

        log.info("Invio messaggio plugin log");
        scodatore.pushMessage(getDemoMessage("DEMO","LOG","OUT1","PAYLOAD"));

        log.info("Invio messaggio plugin mock");
        scodatore.pushMessage(getDemoMessage("DEMO","MOCK","OUT2","PAYLOAD"));

    }

    private static String getDemoMessage(String xbbr_codi_proc, String xbbr_codi_serv, String xbbr_dest_proc, String payload) {
        var message = FMT."""
        %-600s\{""}%-16s\{xbbr_codi_proc}%-16s\{xbbr_codi_serv}%-111s\{""}%-8s\{xbbr_dest_proc}%-365s\{""}%-4s\{"0000"}\{payload}
        """;
        return message;

    }


}
