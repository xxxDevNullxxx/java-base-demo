/**
 *
 */
package it.umana.demo.spi.dto;

import it.umana.demo.spi.annotation.HostStringMessage;
import it.umana.demo.spi.annotation.OffsetLenght;
import it.umana.demo.spi.annotation.Order;

import java.io.Serializable;

/**
 * La classe viene utilizzata per gesitre le informazioni relative all'operazione
 * in essere così come definito nella copy COBOL riportata di seguito.
 * <p/>
 * <pre>
 * {@code
 *
 * +---------------------+---------+--------------------------------+-----+-----+
 * | Campo               | Formato | Descrizione                    | I/O | F/O |
 * +---------------------+---------+--------------------------------+-----+-----+
 * | XBBR-CODI-PROC      | X(16)   | Codice procedura evento broker | I   | O   |
 * +---------------------+---------+--------------------------------+-----+-----+
 * | XBBR-CODI-SERV      | X(16)   | Codice servizio evento broker  | I   | O   |
 * +---------------------+---------+--------------------------------+-----+-----+
 * | XBBR-CODI-OPER      | X(32)   | Codice operazione              | I   | F   |
 * +---------------------+---------+--------------------------------+-----+-----+
 * | XBBR-CODI-USER      | X(08)   | Userid                         | I   | O   |
 * +---------------------+---------+--------------------------------+-----+-----+
 * | XBBR-CODI-TERM      | X(08)   | Codice terminale               | I   | F   |
 * +---------------------+---------+--------------------------------+-----+-----+
 * | XBBR-CODI-ISTI      | 9(3)    | Istituto                       | I   | F   |
 * +---------------------+---------+--------------------------------+-----+-----+
 * | XBBR-CODI-DIPE      | 9(5)    | Dipendenza                     | I   | F   |
 * +---------------------+---------+--------------------------------+-----+-----+
 * | XBBR-CODI-UO        | X(6)    | Operatore UO                   | I   | F   |
 * +---------------------+---------+--------------------------------+-----+-----+
 * | XBBR-CODI-PROV      | X(8)    | Provenienza                    | I   | F   |
 * +---------------------+---------+--------------------------------+-----+-----+
 * | XBBR-CODI-CANA      | X(5)    | Codice canale                  | I   | F   |
 * +---------------------+---------+--------------------------------+-----+-----+
 * | XBBR-SIGL-CANA      | X(3)    | Sigla canale                   | I   | F   |
 * +---------------------+---------+--------------------------------+-----+-----+
 * | XBBR-CODI-GUID      | X(32)   | Chiave operazione del broker   | O   | O   |
 * +---------------------+---------+--------------------------------+-----+-----+
 * | XBBR-FORZA-SINCRONO | X(1)    | Forzatura per richiamo Sincrono| O   | O   |
 * +---------------------+---------+--------------------------------+-----+-----+
 * | XBBR-DEST-PROC      | X(8)    | Destinatario messaggio         | O   | O   |
 * +---------------------+---------+--------------------------------+-----+-----+
 * | XBBR-FILLER         | X(365)  | Riservato usi futuri           | O   | O   |
 * +---------------------+---------+--------------------------------+-----+-----+
 * }
 * </pre>
 *
 *
 * @author ACME
 */
@HostStringMessage()
public class HeaderTecnicoDTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6049684592583447553L;

    /**
     * X(16)   | Codice procedura evento broker
     */
    
    
    @Order(1)
    @OffsetLenght(lenght = 16)
    private String xbbr_codi_proc;

    /**
     * X(16)   | Codice servizio evento broker
     */
    
    
    @Order(2)
    @OffsetLenght(lenght = 16)
    private String xbbr_codi_serv;

    /**
     * X(32)   | Codice operazione
     */
    
    
    @Order(3)
    @OffsetLenght(lenght = 32)
    private String xbbr_codi_oper;

    /**
     * X(08)   | Userid
     */
    
    
    @Order(4)
    @OffsetLenght(lenght = 8)
    private String xbbr_codi_user;

    /**
     * X(08)   | Codice terminale
     */
    
    
    @Order(5)
    @OffsetLenght(lenght = 8)
    private String xbbr_codi_term;

    /**
     * 9(3)    | Istituto
     */
    
    
    @Order(6)
    @OffsetLenght(lenght = 3)
    private String xbbr_codi_isti;

    /**
     * 9(5)    | Dipendenza
     */
    
    
    @Order(7)
    @OffsetLenght(lenght = 5)
    private String xbbr_codi_dipe;

    /**
     * X(6)    | Operatore UO
     */
    
    
    @Order(8)
    @OffsetLenght(lenght = 6)
    private String xbbr_codi_uo;

    /**
     * X(8)    | Provenienza
     */
    
    
    @Order(9)
    @OffsetLenght(lenght = 8)
    private String xbbr_codi_prov;

    /**
     * X(5)    | Codice canale
     */
    
    
    @Order(10)
    @OffsetLenght(lenght = 5)
    private String xbbr_codi_cana;

    /**
     * X(3)    | Sigla canale
     */
    
    
    @Order(11)
    @OffsetLenght(lenght = 3)
    private String xbbr_sigl_cana;

    /**
     * X(32)   | Chiave operazione del broker
     */
    
    
    @Order(12)
    @OffsetLenght(lenght = 32)
    private String xbbr_codi_guid;


    /**
     * X(1)    | Forzatura per richiamo Sincrono
     */
    
    @Order(13)
    @OffsetLenght(lenght = 1)
    private Boolean xbbr_forza_sincrono;

    /**
     * X(8)    | Destinatario messaggio
     */
    
    @Order(14)
    @OffsetLenght(lenght = 8)
    private String xbbr_dest_proc;

    /**
     * X(365)  | Riservato usi futuri
     */
    
    @Order(15)
    @OffsetLenght(lenght = 365)
    private String xbbr_filler;


    public String getXbbr_codi_proc() {
        return xbbr_codi_proc;
    }

    public void setXbbr_codi_proc(String xbbr_codi_proc) {
        this.xbbr_codi_proc = xbbr_codi_proc;
    }

    public String getXbbr_codi_serv() {
        return xbbr_codi_serv;
    }

    public void setXbbr_codi_serv(String xbbr_codi_serv) {
        this.xbbr_codi_serv = xbbr_codi_serv;
    }

    public String getXbbr_codi_oper() {
        return xbbr_codi_oper;
    }

    public void setXbbr_codi_oper(String xbbr_codi_oper) {
        this.xbbr_codi_oper = xbbr_codi_oper;
    }

    public String getXbbr_codi_user() {
        return xbbr_codi_user;
    }

    public void setXbbr_codi_user(String xbbr_codi_user) {
        this.xbbr_codi_user = xbbr_codi_user;
    }

    public String getXbbr_codi_term() {
        return xbbr_codi_term;
    }

    public void setXbbr_codi_term(String xbbr_codi_term) {
        this.xbbr_codi_term = xbbr_codi_term;
    }

    public String getXbbr_codi_isti() {
        return xbbr_codi_isti;
    }

    public void setXbbr_codi_isti(String xbbr_codi_isti) {
        this.xbbr_codi_isti = xbbr_codi_isti;
    }

    public String getXbbr_codi_dipe() {
        return xbbr_codi_dipe;
    }

    public void setXbbr_codi_dipe(String xbbr_codi_dipe) {
        this.xbbr_codi_dipe = xbbr_codi_dipe;
    }

    public String getXbbr_codi_uo() {
        return xbbr_codi_uo;
    }

    public void setXbbr_codi_uo(String xbbr_codi_uo) {
        this.xbbr_codi_uo = xbbr_codi_uo;
    }

    public String getXbbr_codi_prov() {
        return xbbr_codi_prov;
    }

    public void setXbbr_codi_prov(String xbbr_codi_prov) {
        this.xbbr_codi_prov = xbbr_codi_prov;
    }

    public String getXbbr_codi_cana() {
        return xbbr_codi_cana;
    }

    public void setXbbr_codi_cana(String xbbr_codi_cana) {
        this.xbbr_codi_cana = xbbr_codi_cana;
    }

    public String getXbbr_sigl_cana() {
        return xbbr_sigl_cana;
    }

    public void setXbbr_sigl_cana(String xbbr_sigl_cana) {
        this.xbbr_sigl_cana = xbbr_sigl_cana;
    }

    public String getXbbr_codi_guid() {
        return xbbr_codi_guid;
    }

    public void setXbbr_codi_guid(String xbbr_codi_guid) {
        this.xbbr_codi_guid = xbbr_codi_guid;
    }

    public Boolean getXbbr_forza_sincrono() {
        return xbbr_forza_sincrono;
    }

    public void setXbbr_forza_sincrono(Boolean xbbr_forza_sincrono) {
        this.xbbr_forza_sincrono = xbbr_forza_sincrono;
    }

    public String getXbbr_dest_proc() {
        return xbbr_dest_proc;
    }

    public void setXbbr_dest_proc(String xbbr_dest_proc) {
        this.xbbr_dest_proc = xbbr_dest_proc;
    }

    public String getXbbr_filler() {
        return xbbr_filler;
    }

    public void setXbbr_filler(String xbbr_filler) {
        this.xbbr_filler = xbbr_filler;
    }
}
