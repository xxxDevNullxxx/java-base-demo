package it.umana.demo.ib;

import java.io.StringReader;

public class AutenticazioneBase implements MetodoAutenticazione {

    private String nomeUtente;
    private String password;

    public AutenticazioneBase(String nomeUtente, String password) {
        this.nomeUtente = nomeUtente;
        this.password = password;
    }

    @Override
    public boolean valida() {
        return "Utente".equalsIgnoreCase(nomeUtente) && "Password".equals(password);
    }
}
