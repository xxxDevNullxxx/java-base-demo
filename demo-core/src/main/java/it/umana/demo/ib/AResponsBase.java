package it.umana.demo.ib;

public abstract class AResponsBase {

    private boolean successo;
    private String msgEsito;

    public boolean isSuccesso() {
        return successo;
    }

    public void setSuccesso(boolean esito) {
        this.successo = esito;
    }

    public String getMsgEsito() {
        return msgEsito;
    }

    public void setMsgEsito(String msgEsito) {
        this.msgEsito = msgEsito;
    }
}
