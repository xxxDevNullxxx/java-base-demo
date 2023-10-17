package it.umana.demo.logic;

/**
 * @author ACME.
 */

public enum ScodatoreMessageExceptionErrorCodes {
    ERRORE_UNMARSHAL("100", "ERRORE UNMARSHAL", "Errore nella funzione di Unmarshal"),
    ERRORE_PLUGIN("200", "ERRORE PLUGIN", "Errore nella chiamata al plugin"),
    ERRORE_XML("300", "ERRORE XML", "Errore nella richiesta XML per conoscere il nome della coda e del plugin"),
    INVIO_NON_RIUSCITO("400", "ERRORE_INVIO", "Errore nel finally nell'invio del messaggio"),
    ERRORE_MSG_NON_VALIDO("500", "MSG NOT VALID", "Errore nella serializzazione del messaggio");

    protected String errNo;
    protected String errorS;
    protected String errorM;

    ScodatoreMessageExceptionErrorCodes(String errNo, String errorS, String errorM) {
        this.errNo = errNo;
        this.errorS = errorS;
        this.errorM = errorM;
    }

    public String getErrNo() {
        return this.errNo;
    }

    public String getErrorS() {
        return this.errorS;
    }

    public String getErrorM() {
        return this.errorM;
    }
}
