package it.umana.demo.ib;

import java.util.Objects;

public class Beneficiario {
    private String intestazione;
    private String iban;

    public String getIntestazione() {
        return intestazione;
    }

    public void setIntestazione(String intestazione) {
        this.intestazione = intestazione;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Beneficiario(String intestazione, String iban) {
        this.intestazione = intestazione;
        this.iban = iban;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beneficiario that = (Beneficiario) o;
        return Objects.equals(intestazione, that.intestazione) && Objects.equals(iban, that.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(intestazione, iban);
    }

    @Override
    public String toString() {
        return "Beneficiario{" +
                "intestazione='" + intestazione + '\'' +
                ", iban='" + iban + '\'' +
                '}';
    }
}
