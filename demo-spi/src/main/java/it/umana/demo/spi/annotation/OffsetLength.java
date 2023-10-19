package it.umana.demo.spi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotazione che determina la lunghezza dei campi in un tracciato a lunghezza fissa.
 * @author ACME
 */
@Retention(RUNTIME)
@Target(ElementType.FIELD)
public @interface OffsetLength {

    /**
     * Determina la lunghezza del campo
     * @return
     */
    int length() default 0;

    /**
     * Se any è valorizzato a true viene ignorato il campo length
     * e si considera che il campo termini con il fine stringa.
     * @return
     */
    boolean any() default false;

    /**
     * Determina se il campo, dopo essere stato estratto dal tracciato, deve essere
     * trimmato.
     * @return
     */
    boolean trim() default true;
}
