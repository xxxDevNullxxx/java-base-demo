package it.umana.demo.spi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotazione che determina la posizione di un campo in un tracciato a lunghezza fissa.
 * @created ACME
 */
@Retention(RUNTIME)
@Target(ElementType.FIELD)
public @interface Order {

    /**
     * Posizione del campo nel tracciato a lunghezza fissa
     * @return
     */
    int value();
}
