package it.umana.demo.ib;

import java.math.BigDecimal;
import java.util.Objects;

public class ContoCorrente {

    private int numeroConto;
    private String nomeIntestatario;

    public ContoCorrente(int numeroConto, String nomeIntestatario) {
        this.numeroConto = numeroConto;
        this.nomeIntestatario = nomeIntestatario;
    }

    public BigDecimal recuperoSaldo() {
        return new BigDecimal(1000);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContoCorrente that = (ContoCorrente) o;
        return numeroConto == that.numeroConto && Objects.equals(nomeIntestatario, that.nomeIntestatario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroConto, nomeIntestatario);
    }

    @Override
    public String toString() {
        return "ContoCorrente{" + "numeroConto=" + numeroConto + ", nomeIntestatario='" + nomeIntestatario + '\'' + '}';
    }

    public int getNumeroConto() {
        return numeroConto;
    }

    public void setNumeroConto(int numeroConto) {
        this.numeroConto = numeroConto;
    }

    public String getNomeIntestatario() {
        return nomeIntestatario;
    }

    public void setNomeIntestatario(String nomeIntestatario) {
        this.nomeIntestatario = nomeIntestatario;
    }
}
