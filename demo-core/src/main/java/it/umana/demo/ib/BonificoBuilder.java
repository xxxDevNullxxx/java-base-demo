package it.umana.demo.ib;

import java.math.BigDecimal;
import java.util.List;

public class BonificoBuilder {
    private List<MetodoAutenticazione> autenticazioneMultiFattore;
    private OTP autenticazioneAggiuntiva;
    private boolean operazioneDaEstero;
    private ContoCorrente contoCorrenteAddebito;
    private Beneficiario beneficiario;
    private BigDecimal importo;
    private String causale;

    public BonificoBuilder setAutenticazioneMultiFattore(List<MetodoAutenticazione> autenticazioneMultiFattore) {
        this.autenticazioneMultiFattore = autenticazioneMultiFattore;
        return this;
    }

    public BonificoBuilder setAutenticazioneAggiuntiva(OTP autenticazioneAggiuntiva) {
        this.autenticazioneAggiuntiva = autenticazioneAggiuntiva;
        return this;
    }

    public BonificoBuilder setOperazioneDaEstero(boolean operazioneDaEstero) {
        this.operazioneDaEstero = operazioneDaEstero;
        return this;
    }

    public BonificoBuilder setContoCorrenteAddebito(ContoCorrente contoCorrenteAddebito) {
        this.contoCorrenteAddebito = contoCorrenteAddebito;
        return this;
    }

    public BonificoBuilder setBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
        return this;
    }

    public BonificoBuilder setImporto(BigDecimal importo) {
        this.importo = importo;
        return this;
    }

    public BonificoBuilder setCausale(String causale) {
        this.causale = causale;
        return this;
    }

    public Bonifico createBonifico() {
        return new Bonifico(autenticazioneMultiFattore, autenticazioneAggiuntiva, operazioneDaEstero, contoCorrenteAddebito, beneficiario, importo, causale);
    }
}