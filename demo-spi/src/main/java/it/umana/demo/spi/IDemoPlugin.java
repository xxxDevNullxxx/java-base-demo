package it.umana.demo.spi;

import it.umana.demo.spi.dto.HeaderDTO;
import it.umana.demo.spi.dto.MessageDTO;

/**
 * Interfaccia che verrà utilizzata per chiamare i plugin.
 * @author ACME
 */
public interface IDemoPlugin {

    /**
     * Metodo per la gestione di un messaggio
     * @param message Il messaggio da gestire
     * @return HeaderDTO contenente l'esito dell'attività svolta dal plugin.
     */
    HeaderDTO manageMessage(MessageDTO message);

}
