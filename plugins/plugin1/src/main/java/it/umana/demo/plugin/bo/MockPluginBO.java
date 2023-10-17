package it.umana.demo.plugin.bo;


import it.umana.demo.spi.IDemoPlugin;
import it.umana.demo.spi.dto.EXberEsitTypeDTO;
import it.umana.demo.spi.dto.GestioneErroriDTO;
import it.umana.demo.spi.dto.HeaderDTO;
import it.umana.demo.spi.dto.MessageDTO;

/**
 * @author ACME
 * @since 13/04/16
 */
public class MockPluginBO implements IDemoPlugin {
    @Override
    public HeaderDTO manageMessage(MessageDTO message) {

        HeaderDTO header = message.getHeader();

        GestioneErroriDTO gestioneErrori = header.getGestioneErrori();
        gestioneErrori.setXber_esit_rc("00");
        gestioneErrori.setXber_esit_type(EXberEsitTypeDTO.I);

        gestioneErrori.setXber_esit_modulo(MockPluginBO.class.getName());

        return  header;
    }
}
