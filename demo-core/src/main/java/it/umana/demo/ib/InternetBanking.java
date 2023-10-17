package it.umana.demo.ib;

import it.umana.demo.ib.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Un meraviglioso internet banking
 */
public class InternetBanking
{
    public static void main( String[] args )
    {
        eseguiBonifico();
    }

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

        if(bonifico.eseguiOperazione()){
            System.out.println("Bonifico eseguito con successo");
            return true;
        }else{
            System.out.println("Errore durante l'esecuzione di un bonifico");
            return false;
        }

    }
}
