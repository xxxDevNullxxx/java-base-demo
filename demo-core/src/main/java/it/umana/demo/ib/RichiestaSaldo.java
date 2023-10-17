package it.umana.demo.ib;

import it.umana.demo.ib.ContoCorrente;
import it.umana.demo.ib.MetodoAutenticazione;
import it.umana.demo.ib.OTP;
import it.umana.demo.ib.Operazione;

import java.util.List;

public class RichiestaSaldo extends Operazione {

    private ContoCorrente contoCorrente;

    public RichiestaSaldo(List<MetodoAutenticazione> autenticazioneMultiFattore, OTP autenticazioneAggiuntiva, boolean operazioneDaEstero) {
        super(autenticazioneMultiFattore, autenticazioneAggiuntiva, operazioneDaEstero);
    }

    @Override
    protected boolean eseguiOperazioneSpecifica() {
        // ??? Cosa succede se devo restituire un valore?
        // return contoCorrente.recuperoSaldo();
        return true;
    }
}
