package it.umana.demo.spi.annotation;

import it.umana.demo.spi.adapter.HostFieldAdapter;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME) @Target({PACKAGE,FIELD,METHOD,TYPE,PARAMETER})
public @interface HostTypeAdapter {
    /**
     * Points to the class that converts a value type to a bound type or vice versa.
     * See {@link HostFieldAdapter} for more details.
     */
    @SuppressWarnings("rawtypes")
    Class<? extends HostFieldAdapter> value();

    /**
     * If this annotation is used at the package level, then value of
     * the type() must be specified.
     */

    @SuppressWarnings("rawtypes")
    Class type() default DEFAULT.class;

    /**
     * Used in {@link HostTypeAdapter#type()} to
     * signal that the type be inferred from the signature
     * of the field, property, parameter or the class.
     */

    static final class DEFAULT {}

}
