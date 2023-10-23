package it.umana.demo.plugin.deleghe.dto;

import it.umana.demo.spi.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * La classe rappresenta il PDU(Protocol Data Unit) da trattare.
 * Il messaggio è composto da una testata gestita dal broker e da un payload testuale
 * contenente le informazioni applicative da veicolare.
 *
 * @author ACME
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@HostStringMessage()
public class MessaggioDelegaDTO {

    @Order(1)
    @OffsetLength(length = 162)
    private TestataTecnicaDTO testata;

    @Order(2)
    @DynamicOccours(occoursId = "CLIENTI")
    private List<ClienteDTO> lista_clienti;

    @Order(3)
    @DynamicOccours(occoursId = "AZIONI")
    @LastField(terminatorString = "@@@@@@@@")
    private List<AzioneDTO> lista_azioni;
}
