package it.umana.demo.plugin.deleghe.dto;

import it.umana.demo.spi.annotation.HostStringMessage;
import it.umana.demo.spi.annotation.OffsetLength;
import it.umana.demo.spi.annotation.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <pre>
 * {@code
 * |     Campo      |               Descrizione                |    Form.     | Dim. | I/O | F/O |                                Note                                 |
 * |----------------|------------------------------------------|--------------|------|-----|-----|---------------------------------------------------------------------|
 * | TAG            | Identificatore segmento                  | Alfanumerico |    8 |   - |   - | AZIONI                                                              |
 * | Occorrenze     | Numero occorrenze                        | Numerico     |    3 |   - |   - |                                                                     |
 * | Azioni         | Gruppo                                   |              |      |     |     | Il blocco è ripetuto tante volte come indicato al campo precedente. |
 * | Tipo azione    | Codice del tipo azione concessa          | Numerico     |    4 |   - |   - | Almeno una occorrenza è obbligatoria                                |
 * | Descrizione az | Descrizione dell’azione concessa         | Alfanumerico |   50 |   - |   - |                                                                     |
 * | Minimo         | Soglia minima a cui si applica l’azione  | Numerico     | 13,2 |   - |   - |                                                                     |
 * | Massimo        | Soglia massima a cui si applica l’azione | Numerico     | 13,2 |   - |   - |
 * }
 * </pre>
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@HostStringMessage
public class AzioneDTO implements Serializable {

    @Order(1)
    @OffsetLength(length = 8)
    private int tipo_azione;

    @Order(2)
    @OffsetLength(length = 50)
    private String descrizione_azione;

    @Order(3)
    @OffsetLength(length = 15)
    private String minimo;

    @Order(4)
    @OffsetLength(length = 15)
    private String massimo;
}
