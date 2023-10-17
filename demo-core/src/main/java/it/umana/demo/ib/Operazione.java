package it.umana.demo.ib;

import java.util.ArrayList;
import java.util.List;

import static java.util.FormatProcessor.FMT;

public abstract class Operazione {

    private List<MetodoAutenticazione> autenticazioneMultiFattore;
    private OTP autenticazioneAggiuntiva;
    protected boolean operazioneDaEstero;

    public Operazione(List<MetodoAutenticazione> autenticazioneMultiFattore, OTP autenticazioneAggiuntiva, boolean operazioneDaEstero){
        this.autenticazioneMultiFattore = autenticazioneMultiFattore;
        this.autenticazioneAggiuntiva = autenticazioneAggiuntiva;
        this.operazioneDaEstero = operazioneDaEstero;
    }

    public boolean eseguiOperazione(){
        var metodiDiAutenticazioneRichiesti = new ArrayList<>(autenticazioneMultiFattore);

        System.out.println("========================================================================================================");
        System.out.println("Operazione in corso: attendere...");
        if(operazioneDaEstero){
            System.out.println("Attenzione, operazione eseguita dall'estero; necessario otp aggiuntivo");
            metodiDiAutenticazioneRichiesti.add(autenticazioneAggiuntiva);
        }

        return login(metodiDiAutenticazioneRichiesti) && eseguiOperazioneSpecifica();
    }

    public boolean login(List<MetodoAutenticazione> listaMetodiAutenticazione){
        System.out.println("Verifica credenziali di accesso");
//
//        for (MetodoAutenticazione metodoAutenticazione : listaMetodiAutenticazione) {
//            if(!metodoAutenticazione.valida()){
//                return false;
//            }
//        }
//
//        return true;


        boolean esito = listaMetodiAutenticazione.stream()
                //.map(MetodoAutenticazione::valida)
                .map(f -> {
                    System.out.println(
                      FMT."""
                       %-50s\{ "Verfica " + f.getClass().getSimpleName() + " in corso..." } %10s\{ f.valida() ? "OK" : "ERRORE" }"""
                    );
                    return f.valida();
                })
                .allMatch(value -> value);

        return esito;
    }

    protected abstract boolean eseguiOperazioneSpecifica();

}
