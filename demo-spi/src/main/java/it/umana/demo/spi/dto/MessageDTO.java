package it.umana.demo.spi.dto;

import it.umana.demo.spi.annotation.HostStringMessage;
import it.umana.demo.spi.annotation.OffsetLength;
import it.umana.demo.spi.annotation.Order;

import java.nio.ByteBuffer;

/**
 * La classe rappresenta il PDU(Protocol Data Unit) da trattare.
 * Il messaggio è composto da una testata gestita dal broker e da un payload testuale
 * contenente le informazioni applicative da veicolare.
 *
 * @author ACME
 */
@HostStringMessage()
public class MessageDTO {

    /**
     * Encoding EBCDIC utilizzato da HOST
     */
    private static final String ENCODING_HOST = "IBM-1047";

    @Order(1)
    @OffsetLength(length = 1116)
    /**
     * Informazioni di controllo
     */
    private HeaderDTO header;


    /**
     * S9(09) COMP  | Rappresentazione binaria della lunghezza del payload in byte
     *
     * <p>
     * Pur non essendo nella copy COBOL XBBR, tutti i messaggi da veicolare avranno
     * i primi 4 byte che determinano la lunghezza del messaggio.
     * <p>
     *   <pre>
     * {@code
     *
     *ESEMPIO:
     *
     *  I primi 4 byte rappresentano la lunghezza dell’aerea XBBU-BUFF-D .
     *  La lunghezza dell’area GD0 calcolata dal Q2GTYOR è 450 byte che in esadecimale corrisponde a
     *  000001C2 che rappresentato in ebcdic equivale a “...B”
     *
     *05 XBBU-BUFF.
     *  10 XBBU-BUFF-D.
     *  10 XBBU-BUFF-L           PIC S9(09) COMP….
     *  15 FILLER          OCCURS 2097152 TIM…..
     *
     *  XBBU-BUFF         :    B2016-04-28-08.38.23.134434S000010000000002606Q2T
     *  ECCE6CECC44444444474000CFFFF6FF6FF6FF4FF4FF4FFFFFFEFFFFFFFFFFFFFFFFFFDFE
     *  722402466000000000A000122016004028008B38B23B1344342000010000000002606823
     *
     *}
     *   </pre>
     * </p>
     */

    @Order(2)
    @OffsetLength(length = 4,trim = false)
    private String xbbu_buff_l;


    @Order(3)
    @OffsetLength(any = true,trim = false)
    /**
     * Contenuto informativo da veicolare.
     * <p>
     *     La dimensione del payload è variabile. Il campo verrà letto fino a fine stringa
     *     e non verrà eseguito il trim sul valore estratto.
     * </p>
     */
    private String payload;

    public HeaderDTO getHeader() {
        return header;
    }

    public void setHeader(HeaderDTO header) {
        this.header = header;
    }


    public String getXbbu_buff_l() {
        return xbbu_buff_l;
    }

    public void setXbbu_buff_l(String xbbu_buff_l) {
        this.xbbu_buff_l = xbbu_buff_l;
    }

    public Integer getPayloadSize() {
        if(xbbu_buff_l !=null && xbbu_buff_l.length() == 4){
            try{
                return ByteBuffer.wrap(xbbu_buff_l.getBytes(ENCODING_HOST)).getInt();
            }catch(Exception e){
                // Lunghezza non determinabile ritorno null
            }
        }
        return null;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

}
