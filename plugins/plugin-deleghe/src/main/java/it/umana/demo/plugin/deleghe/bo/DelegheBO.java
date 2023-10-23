package it.umana.demo.plugin.deleghe.bo;

import it.umana.demo.plugin.deleghe.dto.MessaggioDelegaDTO;
import it.umana.demo.spi.HostMessageAdapter;
import it.umana.demo.spi.IDemoPlugin;
import it.umana.demo.spi.dto.EXberEsitTypeDTO;
import it.umana.demo.spi.dto.GestioneErroriDTO;
import it.umana.demo.spi.dto.HeaderDTO;
import it.umana.demo.spi.dto.MessageDTO;
import lombok.extern.java.Log;

@Log
public class DelegheBO implements IDemoPlugin {
    @Override
    public HeaderDTO manageMessage(MessageDTO message) {

        HeaderDTO header = message.getHeader();

        GestioneErroriDTO gestioneErrori = header.getGestioneErrori();
        gestioneErrori.setXber_esit_rc("00");
        gestioneErrori.setXber_esit_type(EXberEsitTypeDTO.I);

        gestioneErrori.setXber_esit_modulo(HeaderDTO.class.getName());

        try{
            MessaggioDelegaDTO messagioDelegaDTO;
            HostMessageAdapter hostMessageAdapter = new HostMessageAdapter();
            messagioDelegaDTO = (MessaggioDelegaDTO) hostMessageAdapter.unmarshal(message.getPayload(), MessaggioDelegaDTO.class);
            System.out.println("TESTATA TECNICA: " + messagioDelegaDTO.getTestata().toString());

        } catch (Exception e) {
            log.severe(e.getMessage());
        }
        return  header;
    }
}
