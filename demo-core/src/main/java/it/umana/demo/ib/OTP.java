package it.umana.demo.ib;

public class OTP implements MetodoAutenticazione {


    public static final int valoreOtpValido = 123456789;
    private int valoreOtp;

    public OTP(int valoreOtp) {
        this.valoreOtp = valoreOtp;
    }

    @Override
    public boolean valida() {
        return valoreOtpValido == this.valoreOtp;
    }

}
