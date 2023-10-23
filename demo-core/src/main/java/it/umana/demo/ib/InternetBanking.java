package it.umana.demo.ib;

import it.umana.demo.ib.*;
import lombok.SneakyThrows;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Un meraviglioso internet banking
 */
public class InternetBanking
{
    @SneakyThrows
    public static void main( String[] args ) {
        //eseguiBonifico();
        visualizzaSaldo();
    }

    @SneakyThrows
    public static boolean eseguiBonifico() {

        byte[] datiImpronta = {(byte) 0xFF, (byte) 0x00,(byte) 0xFF, (byte) 0x00};
        List<MetodoAutenticazione> autenticazioneMultiFattore = Arrays.asList(
                new AutenticazioneBase("utente","Password"),
                new ImprontaDigitale(datiImpronta)
        );

        Bonifico bonifico = new BonificoBuilder()
                .setCausale("Bonifico di prova")
                .setImporto(new BigDecimal(5000))
                .setContoCorrenteAddebito(new ContoCorrente(1,"Carlo Bianchi"))
                .setBeneficiario(new Beneficiario("Rossi Mario","IT05W0303200000000000000001"))
                .setAutenticazioneMultiFattore(autenticazioneMultiFattore)
                .setOperazioneDaEstero(true)
                .setAutenticazioneAggiuntiva(new OTP(123456789))
                .createBonifico();

        if (bonifico.eseguiOperazione(BonificoRespons.class).isSuccesso()){
            System.out.println("Bonifico eseguito con successo");
            return true;
        } else {
            System.out.println("Errore durante l'esecuzione di un bonifico");
            return false;
        }

    }

    public static boolean visualizzaSaldo() {
        byte[] datiImpronta = {(byte) 0xFF, (byte) 0x00,(byte) 0xFF, (byte) 0x00};
        List<MetodoAutenticazione> autenticazioneMultiFattore = Arrays.asList(
                new AutenticazioneBase("utente","Password"),
                new ImprontaDigitale(datiImpronta)
        );
        RichiestaSaldo richiestaSaldo = new RichiestaSaldo(autenticazioneMultiFattore,new OTP(123456789), true);
        RichiestaSaldoRespons richiestaSaldoRespons = richiestaSaldo.eseguiOperazioneSpecifica();
        if (richiestaSaldoRespons.isSuccesso()) {
           System.out.printf("Il saldo corrente è: %.2f", richiestaSaldoRespons.getSaldo());
        } else {
            System.out.println("la richiesta saldo non è andata a buon fine!");
        }
        return true;
    }
}
