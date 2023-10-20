package it.umana.demo.plugin.deleghe.dto;


import it.umana.demo.spi.annotation.HostStringMessage;
import it.umana.demo.spi.annotation.OffsetLength;
import it.umana.demo.spi.annotation.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * * <pre>
 *  * {@code
 * | Campo              | Descrizione                        |    Form.     | Dim. | Note                                                                                                                            |
 * |--------------------|------------------------------------|--------------|------|---------------------------------------------------------------------------------------------------------------------------------|
 * | Funzione           | Tipo funzione richiesta            | Alfanumerico |    3 | Tipo funzione:<br/>UPD: Aggiornamento di una delega</br>DEL: Eliminazione di una delega</br>INS: Inserimento di una delega</br> |
 * | Istituto           | Codice istituto                    | Numerico     |    5 |                                                                                                                                 |
 * | Numero delega      | Numero identificativo della delega | Numerico     |   10 |                                                                                                                                 |
 * | Tipo legame        | Tipo legame                        | Numerico     |    4 |                                                                                                                                 |
 * | Descrizione legame | Descrizione del tipo legame        | Alfanumerico |   50 |                                                                                                                                 |
 * | Tipo firma         | Tipo firma                         | Numerico     |    4 |                                                                                                                                 |
 * | Descrizione firma  | Descrizione tipo firma             | Alfanumerico |   50 |                                                                                                                                 |
 * | Data inizio        | Data inizio validità della delega  | Data         |    - |                                                                                                                                 |
 * | Data fine          | Data fine validità della delega    | Data         |    - |                                                                                                                                 |
 * | Servizio           | Codice servizio del rapporto       | Numerico     |    3 |                                                                                                                                 |
 * | Categoria          | Codice categoria del rapporto      | Numerico     |    3 |                                                                                                                                 |
 * | Filiale            | Codice filiale del rapporto        | Numerico     |    5 |                                                                                                                                 |
 * | Numero             | Numero del rapporto                | Numerico     |    6 |                                                                                                                                 |
 * | Sottonumero        | Sottonumero del rapporto           | Numerico     |    3 |
 * }
 * </pre>
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@HostStringMessage
public class TestataTecnicaDTO implements Serializable {

    @Order(1)
    @OffsetLength(length = 3)
    private String function;

    @Order(2)
    @OffsetLength(length = 5)
    private String istituto;

    @Order(3)
    @OffsetLength(length = 10)
    private String numero_delega;

    @Order(4)
    @OffsetLength(length = 4)
    private String tipo_legame;

    @Order(5)
    @OffsetLength(length = 50)
    private String descrizione_legame;

    @Order(6)
    @OffsetLength(length = 4)
    private String tipo_firma;

    @Order(7)
    @OffsetLength(length = 50)
    private String descrizione_firma;

    @Order(8)
    @OffsetLength(length = 8)
    private String data_inizio;

    @Order(9)
    @OffsetLength(length = 8)
    private String data_fine;

    @Order(10)
    @OffsetLength(length = 3)
    private String Servizio;

    @Order(11)
    @OffsetLength(length = 3)
    private String categoria;

    @Order(12)
    @OffsetLength(length = 5)
    private String filiale;

    @Order(13)
    @OffsetLength(length = 6)
    private String numero;

    @Order(14)
    @OffsetLength(length = 3)
    private String sottonumero;

}
