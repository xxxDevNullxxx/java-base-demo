package it.umana.demo.spi.annotation;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotazione che determina che la classe è associata ad un tracciato a lunghezza fissa.
 * @created ACME
 */
@Retention(RUNTIME)
public @interface HostStringMessage {
    int messageLength() default 0;
}
