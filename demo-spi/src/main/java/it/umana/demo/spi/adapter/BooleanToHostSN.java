package it.umana.demo.spi.adapter;

/**
 * Mappa un elemento Boolean secondo quanto segue:
 * true     <=> "S"
 * false    <=> "N"
 * null     <=> ""
 *
 * @author ACME
 */
public class BooleanToHostSN extends HostFieldAdapter<String, Boolean> {

    private static final String VALUE_TRUE = "S";
    private static final String VALUE_FALSE = "N";

    @Override
    public Boolean unmarshal(String value) throws Exception {
        return value.trim().equalsIgnoreCase(VALUE_TRUE);
    }

    @Override
    public String marshal(Boolean aBoolean) throws Exception {
        if (aBoolean == null) {
            return "";
        }
        return (aBoolean) ? VALUE_TRUE : VALUE_FALSE;
    }
}