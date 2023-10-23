package it.umana.demo.ib;

import lombok.SneakyThrows;

import java.lang.reflect.InvocationTargetException;
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

    @SneakyThrows
    public <R extends AResponsBase> R eseguiOperazione(Class<R> cls) {
        var metodiDiAutenticazioneRichiesti = new ArrayList<>(autenticazioneMultiFattore);

        System.out.println("========================================================================================================");
        System.out.println("Operazione in corso: attendere...");
        if (operazioneDaEstero) {
            System.out.println("Attenzione, operazione eseguita dall'estero; necessario otp aggiuntivo");
            metodiDiAutenticazioneRichiesti.add(autenticazioneAggiuntiva);
        }

        if (!login(metodiDiAutenticazioneRichiesti)) {
            R response = cls.getDeclaredConstructor().newInstance();
            response.setSuccesso(false);
            response.setMsgEsito("Login fallito");
            return response;
        }
        return eseguiOperazioneSpecifica();
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

    protected abstract <R extends AResponsBase> R eseguiOperazioneSpecifica();

}
