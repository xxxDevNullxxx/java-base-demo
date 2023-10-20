package it.umana.demo.spi.manager;

import java.lang.reflect.Field;
import java.util.List;

/**
 *
 *
 */
public interface IDynamicOccoursManager {

    public String marshal(Object paramObject, Field paramField);

    /**
     * Deserializza una classe annotata con DynamicOccoursDictionary partendo da un tracciato a lunghezza fissa
     *
     * @param message Una stringa che rispetti il tracciato a lunghezza fissa
     * @param field   Il campo da serializzare
     * @param <T>     La classe da deserializzare
     * @return Un' istanza della classe T valorizzata partendo dal tracciato
     */
    public <T> List<T> unmarshal(String paramString, Field paramField);
}
