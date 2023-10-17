package it.umana.demo.spi.dto;

import it.umana.demo.spi.annotation.HostStringMessage;
import it.umana.demo.spi.annotation.OffsetLenght;
import it.umana.demo.spi.annotation.Order;

import java.io.Serializable;

/**
 * La classe rappresenta la PCI(Protocol Control Information)
 * La testata è composta da un header tecnico e da un elemento per la gestione degli errori
 * @author ACME
 */
@HostStringMessage()
public class HeaderDTO implements Serializable{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1589112020684675574L;

    /**
     * Informazioni per la gestione degli esiti relativi alla transazione.
     */
    

    @Order(1)
    @OffsetLenght(lenght = 600)
    private GestioneErroriDTO gestioneErrori;

    /**
     * Informazioni per l'identificazione della transazione in corso.
     */
    
    
    @Order(2)
    @OffsetLenght(lenght = 516)
    private HeaderTecnicoDTO headerTecnicoDTO;

    public GestioneErroriDTO getGestioneErrori() {
        return gestioneErrori;
    }

    public void setGestioneErrori(GestioneErroriDTO gestioneErrori) {
        this.gestioneErrori = gestioneErrori;
    }

    public HeaderTecnicoDTO getHeaderTecnicoDTO() {
        return headerTecnicoDTO;
    }

    public void setHeaderTecnicoDTO(HeaderTecnicoDTO headerTecnicoDTO) {
        this.headerTecnicoDTO = headerTecnicoDTO;
    }
}
