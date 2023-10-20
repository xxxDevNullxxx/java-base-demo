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
 * |   Campo    |       Descrizione       |    Form.     | Dim. | I/O | F/O | Note                                                                                                                                                                     |
 * |------------|-------------------------|--------------|------|-----|-----|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
 * | TAG        | Identificatore segmento | Alfanumerico |    8 |   - |   - | CLIENTI                                                                                                                                                                  |
 * | Occorrenze | Numero occorrenze       | Numerico     |    3 |   - |   - |                                                                                                                                                                          |
 * | CDG        | Codice cliente          | Alfanumerico |   13 |   - |   - | Questo campo sarà presente tante volte a seconda del valore del campo precedente.<br/>Il dato riporta gli zeri a sinistra per raggiungere la dimensione di 13 caratteri. |
 * }
 * </pre>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@HostStringMessage
public class ClienteDTO implements Serializable {

    @Order(1)
    @OffsetLength(length = 13)
    private String cdg;
}
