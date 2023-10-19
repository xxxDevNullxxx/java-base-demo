package it.umana.demo.spi.dto;

import it.umana.demo.spi.adapter.BooleanToHostSN;
import it.umana.demo.spi.annotation.HostStringMessage;
import it.umana.demo.spi.annotation.OffsetLength;
import it.umana.demo.spi.annotation.Order;
import it.umana.demo.spi.annotation.HostTypeAdapter;

import java.io.Serializable;

/**
 * La classe viene utilizzata per gesitre le informazioni sugli esiti delle operazioni così come
 * definito nella copy COBOL (600 bytes) riportata di seguito.
 * <pre>
 * {@code
 *
 * +-------------------+---------+----------------------------------------------------+-----+-----+
 * | Campo             | Formato | Descrizione                                        | I/O | F/O |
 * +-------------------+---------+----------------------------------------------------+-----+-----+
 * | XBER-ESIT-RC      | X(2)    | Assume i valori: '00'->OK, da '01' a '99'-> ERRORE | O   | O   |
 * +-------------------+---------+----------------------------------------------------+-----+-----+
 * | XBER-ESIT-PREF    | X(2)    | Prefisso codice                                    | O   | O   |
 * +-------------------+---------+----------------------------------------------------+-----+-----+
 * | FILLER            | X       |                                                    | O   | O   |
 * +-------------------+---------+----------------------------------------------------+-----+-----+
 * | XBER-ESIT-ERRO    | X(3)    | Codice                                             | O   | O   |
 * +-------------------+---------+----------------------------------------------------+-----+-----+
 * | FILLER            | X       |                                                    | O   | O   |
 * +-------------------+---------+----------------------------------------------------+-----+-----+
 * | XBER-ESIT-TYPE    | X       | Tipo E (Errore) W (Warning) I (Informazione)       | O   | O   |
 * +-------------------+---------+----------------------------------------------------+-----+-----+
 * | XBER-ESIT-RIFE    | X(3)    | Riferimento dell’errore riscontrato nel programma  | O   | O   |
 * +-------------------+---------+----------------------------------------------------+-----+-----+
 * | XBER-ESIT-MODULO  | X(8)    | Modulo in errore                                   | O   | O   |
 * +-------------------+---------+----------------------------------------------------+-----+-----+
 * | XBER-ESIT-CAMPO   | X(50)   | Campo in errore                                    | O   | O   |
 * +-------------------+---------+----------------------------------------------------+-----+-----+
 * | XBER-ESIT-DESC-S  | X(20)   | Descrizione small                                  | O   | O   |
 * +-------------------+---------+----------------------------------------------------+-----+-----+
 * | XBER-ESIT-DESC-M  | X(80)   | Descrizione medium                                 | O   | O   |
 * +-------------------+---------+----------------------------------------------------+-----+-----+
 * | XBER-ESIT-DESC-L  | X(256)  | Descrizione big                                    | O   | O   |
 * +-------------------+---------+----------------------------------------------------+-----+-----+
 * | XBER-ESIT-SQLCODE | -999    | SQLCode di errore                                  | O   | O   |
 * +-------------------+---------+----------------------------------------------------+-----+-----+
 * | XBER-ESIT-SQLCA   | X(136)  | Sqlca per DB2                                      | O   | O   |
 * +-------------------+---------+----------------------------------------------------+-----+-----+
 * | XBER-ESIT-DATI    | X(1)    | Indica se è consigliato rollback dei dati          | O   | O   |
 * +-------------------+---------+----------------------------------------------------+-----+-----+
 * | XBER-ESIT-SYSTEM  | X(1)    | Indica se è consigliato l’abend                    | O   | O   |
 * +-------------------+---------+----------------------------------------------------+-----+-----+
 * | FILLER            | X(31)   |                                                    | O   | O   |
 * +-------------------+---------+----------------------------------------------------+-----+-----+
 * }
 * </pre>
 *
 * @author ACME
 */
@HostStringMessage()
public class GestioneErroriDTO implements Serializable {


    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4434597689987560203L;

    /**
     * Definisce l'esito dell'operazione.
     * <p>
     * Assume i valori:
     * </p>
     * <ul>
     * <li>'00' se esito OK</li>
     * <li>da '01' a '99' se l'esito non è OK</li>
     * </ul>
     */

    

    @Order(1)
    @OffsetLength(length = 2)
    private String xber_esit_rc;

    /**
     * X(2)    | Prefisso codice
     */
    
    @Order(2)
    @OffsetLength(length = 2)
    private String xber_esit_pref;
    /**
     * X | Filler HOST mantenuto per gestire la serializzazione
     */
    
    
    @Order(3)

    @OffsetLength(length = 1)
    private String xber_esit_filler_01;
    /**
     * X(3)    | Codice errore
     */
    
    
    @Order(4)
    @OffsetLength(length = 3)
    private String xber_esit_erro;
    /**
     * X | Filler HOST mantenuto per gestire la serializzazione
     */
    
    
    @Order(5)
    @OffsetLength(length = 1)
    private String xber_esit_filler_02;
    /**
     * X       | Tipologia dell'esito
     */

    
/*    */
    @Order(6)
    @OffsetLength(length = 1)
    private EXberEsitTypeDTO xber_esit_type;
    /**
     * X(3)    | Riferimento dell’errore riscontrato nel programma
     */
    
    
    @Order(7)
    @OffsetLength(length = 3)
    private String xber_esit_rife;
    /**
     * X(8)    | Modulo in errore
     */
    
    
    @Order(8)
    @OffsetLength(length = 8)
    private String xber_esit_modulo;
    /**
     * X(50)   | Campo in errore
     */
    
    
    @Order(9)
    @OffsetLength(length = 50)
    private String xber_esit_campo;
    /**
     * X(20)   | Descrizione small
     */
    
    
    @Order(10)
    @OffsetLength(length = 20)
    private String xber_esit_desc_s;
    /**
     * X(80)   | Descrizione medium
     */
    
    
    @Order(11)
    @OffsetLength(length = 80)
    private String xber_esit_desc_m;
    /**
     * X(256)  | Descrizione big
     */
    
    
    @Order(12)
    @OffsetLength(length = 256)
    private String xber_esit_desc_l;
    /**
     * -999    | SQLCode di errore
     */
    

    @Order(13)
    @OffsetLength(length = 4)
    private String xber_esit_sqlcode;
    /**
     * X(136)  | Sqlca per DB2
     */
    
    
    @Order(14)
    @OffsetLength(length = 136)
    private String xber_esit_sqlca;
    /**
     * X(1)    | Indica se è consigliato rollback dei dati
     */
    
    @Order(15)
    @OffsetLength(length = 1)
    @HostTypeAdapter(BooleanToHostSN.class)
    private Boolean xber_esit_dati;
    /**
     * X(1)    | Indica se è consigliato l’abend
     */
    
    @Order(16)
    @OffsetLength(length = 1)
    @HostTypeAdapter(BooleanToHostSN.class)
    private Boolean xber_esit_system;
    /**
     * X(31)   | Filler HOST mantenuto per gestire la serializzazione
     */
    
    
    @Order(17)
    @OffsetLength(length = 31)
    private String xber_esit_filler_03;

    public String getXber_esit_rc() {
        return xber_esit_rc;
    }

    public void setXber_esit_rc(String xber_esit_rc) {
        this.xber_esit_rc = xber_esit_rc;
    }

    public String getXber_esit_pref() {
        return xber_esit_pref;
    }

    public void setXber_esit_pref(String xber_esit_pref) {
        this.xber_esit_pref = xber_esit_pref;
    }

    public String getXber_esit_filler_01() {
        return xber_esit_filler_01;
    }

    public void setXber_esit_filler_01(String xber_esit_filler_01) {
        this.xber_esit_filler_01 = xber_esit_filler_01;
    }

    public String getXber_esit_erro() {
        return xber_esit_erro;
    }

    public void setXber_esit_erro(String xber_esit_erro) {
        this.xber_esit_erro = xber_esit_erro;
    }

    public String getXber_esit_filler_02() {
        return xber_esit_filler_02;
    }

    public void setXber_esit_filler_02(String xber_esit_filler_02) {
        this.xber_esit_filler_02 = xber_esit_filler_02;
    }

    public EXberEsitTypeDTO getXber_esit_type() {
        return xber_esit_type;
    }

    public void setXber_esit_type(EXberEsitTypeDTO xber_esit_type) {
        this.xber_esit_type = xber_esit_type;
    }

    public String getXber_esit_rife() {
        return xber_esit_rife;
    }

    public void setXber_esit_rife(String xber_esit_rife) {
        this.xber_esit_rife = xber_esit_rife;
    }

    public String getXber_esit_modulo() {
        return xber_esit_modulo;
    }

    public void setXber_esit_modulo(String xber_esit_modulo) {
        this.xber_esit_modulo = xber_esit_modulo;
    }

    public String getXber_esit_campo() {
        return xber_esit_campo;
    }

    public void setXber_esit_campo(String xber_esit_campo) {
        this.xber_esit_campo = xber_esit_campo;
    }

    public String getXber_esit_desc_s() {
        return xber_esit_desc_s;
    }

    public void setXber_esit_desc_s(String xber_esit_desc_s) {
        this.xber_esit_desc_s = xber_esit_desc_s;
    }

    public String getXber_esit_desc_m() {
        return xber_esit_desc_m;
    }

    public void setXber_esit_desc_m(String xber_esit_desc_m) {
        this.xber_esit_desc_m = xber_esit_desc_m;
    }

    public String getXber_esit_desc_l() {
        return xber_esit_desc_l;
    }

    public void setXber_esit_desc_l(String xber_esit_desc_l) {
        this.xber_esit_desc_l = xber_esit_desc_l;
    }

    public String getXber_esit_sqlcode() {
        return xber_esit_sqlcode;
    }

    public void setXber_esit_sqlcode(String xber_esit_sqlcode) {
        this.xber_esit_sqlcode = xber_esit_sqlcode;
    }

    public String getXber_esit_sqlca() {
        return xber_esit_sqlca;
    }

    public void setXber_esit_sqlca(String xber_esit_sqlca) {
        this.xber_esit_sqlca = xber_esit_sqlca;
    }

    public Boolean getXber_esit_dati() {
        return xber_esit_dati;
    }

    public void setXber_esit_dati(Boolean xber_esit_dati) {
        this.xber_esit_dati = xber_esit_dati;
    }

    public Boolean getXber_esit_system() {
        return xber_esit_system;
    }

    public void setXber_esit_system(Boolean xber_esit_system) {
        this.xber_esit_system = xber_esit_system;
    }

    public String getXber_esit_filler_03() {
        return xber_esit_filler_03;
    }

    public void setXber_esit_filler_03(String xber_esit_filler_03) {
        this.xber_esit_filler_03 = xber_esit_filler_03;
    }
}