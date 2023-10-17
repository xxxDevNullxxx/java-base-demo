package it.umana.demo.ib;

import java.util.Arrays;
import java.util.HexFormat;

public class ImprontaDigitale implements MetodoAutenticazione {

    public static final byte[] improntaRegistrata = HexFormat.of().parseHex("FF00FF00");
    private byte[] datiImpronta;

    public ImprontaDigitale(byte[] datiImpronta) {
        this.datiImpronta = datiImpronta;
    }

    @Override
    public boolean valida() {
        return Arrays.equals(improntaRegistrata, datiImpronta);
    }
}
