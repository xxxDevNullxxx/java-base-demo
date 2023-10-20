package it.umana.demo.spi.annotation;



import it.umana.demo.spi.manager.BaseDynamicOccoursManager;
import it.umana.demo.spi.manager.IDynamicOccoursManager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotazione che rappresenta una lista tipizzata di {@link HostStringMessage}
 */
@Retention(RUNTIME)
@Target(ElementType.FIELD)
public @interface DynamicOccours {

    /**
     * Il manager che determina nome e lunghezza della lista.
     * @return
     */
    IDynamicOccoursManager DynamicOccoursManager = new BaseDynamicOccoursManager();


    /**
     * Identifica il blocco relativo all'occorrenza
     * @return
     */
    String occoursId() default "";
}
