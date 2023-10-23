package it.umana.demo.ib;

import java.util.List;

public class RichiestaSaldo extends Operazione {

    private ContoCorrente contoCorrente;

    public RichiestaSaldo(List<MetodoAutenticazione> autenticazioneMultiFattore, OTP autenticazioneAggiuntiva, boolean operazioneDaEstero) {
        super(autenticazioneMultiFattore, autenticazioneAggiuntiva, operazioneDaEstero);
    }

    @Override
    protected RichiestaSaldoRespons eseguiOperazioneSpecifica() {
        // ??? Cosa succede se devo restituire un valore?
        // return contoCorrente.recuperoSaldo();
        //return true;
        RichiestaSaldoRespons response = new RichiestaSaldoRespons();
        response.setSaldo(1000);
        response.setSuccesso(true);
        return response;
    }
}
