package it.umana.demo.spi.adapter;

/**
 * La classe prevede di eseguire una trasformazione di formato durante le fasi di serializzazione e deserializzazione
 * @param <ValueType> Questo è il tipo dato che il messaggio serializzato gestisce in maniera nativa
 * @param <BoundType> Questo è il tipo dato con il quale vogliamo rappresentare l'informazione in memoria
 */
public abstract class HostFieldAdapter<ValueType, BoundType> {

    /**
     * Costruttore protetto vuoto
     */
    protected HostFieldAdapter() {}

    /**
     * Deserializza
     * @param value Il valore da deserializzare
     * @return Il dato deserializzato

     * @throws Exception
     */
    public abstract BoundType unmarshal(ValueType value) throws Exception;

    /**
     *
     * @param boundType Il valore da serializzare
     * @return Il valore serializzato
     * @throws Exception
     */
    public abstract  ValueType marshal(BoundType boundType) throws Exception;

}
