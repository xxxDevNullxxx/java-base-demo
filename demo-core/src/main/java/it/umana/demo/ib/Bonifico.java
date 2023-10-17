package it.umana.demo.ib;

import java.math.BigDecimal;
import java.util.List;

import static java.util.FormatProcessor.FMT;

public class Bonifico extends Operazione{

    private ContoCorrente contoCorrenteAddebito;
    private Beneficiario beneficiario;
    private BigDecimal importo;
    private String causale;

    public Bonifico(List<MetodoAutenticazione> autenticazioneMultiFattore, OTP autenticazioneAggiuntiva, boolean operazioneDaEstero, ContoCorrente contoCorrenteAddebito, Beneficiario beneficiario, BigDecimal importo, String causale) {
        super(autenticazioneMultiFattore, autenticazioneAggiuntiva, operazioneDaEstero);
        this.contoCorrenteAddebito = contoCorrenteAddebito;
        this.beneficiario = beneficiario;
        this.importo = importo;
        this.causale = causale;
    }


    @Override
    protected boolean eseguiOperazioneSpecifica() {
//        System.out.println("Eseguo bonifico:");
//        System.out.println(String.format("Da CC: %s ", contoCorrenteAddebito.toString()));
//        System.out.println(String.format("Beneficiario: %s ", beneficiario.toString()));

        System.out.println(
        FMT."""
            === Eseguo bonifico: ===================================================================================
            %-20s\{"Da CC:"} \{contoCorrenteAddebito}
            %-20s\{"Beneficiario:"} \{beneficiario}
            %-20s\{"Importo:"} \{importo}
            %-20s\{"Causale:"} \{causale}
            %-20s\{"Si sta operando da:"} \{operazioneDaEstero?"Estero":"Italia"}
            ========================================================================================================
            """
        );
        return true;
    }

    public ContoCorrente getContoCorrenteAddebito() {
        return contoCorrenteAddebito;
    }

    public void setContoCorrenteAddebito(ContoCorrente contoCorrenteAddebito) {
        this.contoCorrenteAddebito = contoCorrenteAddebito;
    }

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }

    public BigDecimal getImporto() {
        return importo;
    }

    public void setImporto(BigDecimal importo) {
        this.importo = importo;
    }

    public String getCausale() {
        return causale;
    }

    public void setCausale(String causale) {
        this.causale = causale;
    }
}
