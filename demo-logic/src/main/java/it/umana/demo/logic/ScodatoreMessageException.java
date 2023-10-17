package it.umana.demo.logic;

import it.umana.demo.spi.dto.GestioneErroriDTO;
import it.umana.demo.spi.dto.HeaderDTO;

/**
 * @author ACME.
 */

public class ScodatoreMessageException extends Exception {

    private final ScodatoreMessageExceptionErrorCodes errorCode;

    private final Exception exception;

    public ScodatoreMessageException(ScodatoreMessageExceptionErrorCodes errorCodes, Exception e) {
        super(e);
        this.errorCode = errorCodes;
        exception = e;
    }

    public GestioneErroriDTO getGestioneErroriDTO() {
        HeaderDTO HEADER_DTO = HeaderDTOFactory.createHeaderDTO();
        HEADER_DTO.getGestioneErrori().setXber_esit_erro(errorCode.getErrNo());
        HEADER_DTO.getGestioneErrori().setXber_esit_desc_s(errorCode.getErrorS());
        HEADER_DTO.getGestioneErrori().setXber_esit_desc_m(errorCode.getErrorM());
        HEADER_DTO.getGestioneErrori().setXber_esit_desc_l(exception.toString());
        return HEADER_DTO.getGestioneErrori();
    }

    public String getErrors() {
        return ("ERRORE: ".concat(this.errorCode.getErrorS()).concat(", Descrizione: ").concat(this.errorCode.getErrorM()));
    }

    public String getErrorCode() {
        return this.errorCode.errNo;
    }
}
