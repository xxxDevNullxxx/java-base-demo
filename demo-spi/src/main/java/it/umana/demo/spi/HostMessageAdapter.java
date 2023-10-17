package it.umana.demo.spi;

import it.umana.demo.spi.adapter.HostFieldAdapter;
import it.umana.demo.spi.annotation.*;
import it.umana.demo.spi.dto.EXberEsitTypeDTO;
import lombok.extern.java.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @since 11/11/15
 */
@Log
public class HostMessageAdapter {


    public String marshal(Object obj) {

        try {
            int indexReadFromMessage = 0;
            StringBuilder sb = new StringBuilder();
            Class clazz = obj.getClass();

//      If is the class has a HostStringMessage.class
            if (clazz.isAnnotationPresent(HostStringMessage.class)) {

                List<Field> orderFieldsList = getFieldListSortedByOrderAnnotation(clazz);

                for (Field field : orderFieldsList) {

                    if (field.isAnnotationPresent(OffsetLenght.class)) {

                        if (!field.isAccessible()) {
                            field.setAccessible(true);
                        }

                        OffsetLenght annotation = field.getAnnotation(OffsetLenght.class);
                        int lenghtField = annotation.lenght();

                        String fieldValue = this.marshal(field.get(obj));
                        if (!annotation.any()) {
                            fieldValue = (fieldValue.length() > lenghtField) ? fieldValue.substring(0, lenghtField) : fieldValue;
                            sb.append(fieldValue);
                            fieldValue = (fieldValue == null) ? "" : fieldValue;
                            for (int i = 0, l = lenghtField - fieldValue.length(); i < l; i++) {
                                sb.append(" ");
                            }
                        } else {
                            sb.append(fieldValue);
                        }
                    }
                }

            } else {
                //Else the class is a primitive Class
                if (obj == null) return "";
                if (obj instanceof Boolean) return ((Boolean) obj) ? "S" : "N";
                String objectString = obj.toString();
                objectString = Normalizer.normalize(objectString, Normalizer.Form.NFD).replaceAll("[^\\x00-\\x7F]", "");
                return objectString;
            }

            return sb.toString();

        } catch (Exception e) {
            log.severe(e.toString());
        }
        return null;
    }

    /**
     * Deserializza una classe partendo da un tracciato a lunghezza fissa
     *
     * @param message Una stringa che rispetti il tracciato a lunghezza fissa
     * @param clazz   La classe da deserializzare
     * @param <T>     La classe da deserializzare
     * @return Un' istanza della classe T valorizzata partendo dal tracciato
     */
    public <T> T unmarshal(String message, Class clazz) {
        return unmarshal(message, clazz, null);
    }

    private <T> T unmarshal(String message, Class clazz, Field currentField) {

        try {
            int indexReadFromMessage = 0;

            Constructor<?> ctor;
            Object objectInstance;

//      If is the class has a HostStringMessage.class
            if (clazz.isAnnotationPresent(HostStringMessage.class)) {

                ctor = clazz.getConstructor();
                objectInstance = ctor.newInstance();
                List<Field> orderFieldsList = getFieldListSortedByOrderAnnotation(clazz);

                for (Field field : orderFieldsList) {

                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }

                    Object fieldValue = null;

                    if (field.isAnnotationPresent(OffsetLenght.class)) {
                        OffsetLenght annotation = field.getAnnotation(OffsetLenght.class);
                        int lenghtField = annotation.lenght();
                        int endValue = (annotation.any()) ? message.length() : indexReadFromMessage + lenghtField;
                        if (message.length() < endValue) {
                            throw new Exception(String.format("La lunghezza %d non è valida per il campo %s", lenghtField, field.toGenericString()));
                        }
                        String partialMessage = message.substring(indexReadFromMessage, endValue);
                        fieldValue = this.unmarshal(partialMessage, field.getType(), field);

                        indexReadFromMessage = endValue;
                    }


                    field.set(objectInstance, fieldValue);

                }
            } else {
                // Else the class is a primitive Class
                objectInstance = adapterString(message, clazz, currentField);

            }

            return (T) objectInstance;

        } catch (Exception e) {
            log.severe(e.toString());
        }
        return null;
    }

    public List<Field> getFieldListSortedByOrderAnnotation(Class clazz) {

        Field[] abstractfields = clazz.getDeclaredFields();
        List<Field> orderFields = new ArrayList<Field>();

        for (Field field : abstractfields) {

            if (field.isAnnotationPresent(Order.class)) {
                int order = field.getAnnotation(Order.class).value() - 1;
                Field oField = field;
                // se è astratto cerco di ottenere il declared dalla classe in caso di override
                try {

                    Field dField = clazz.getDeclaredField(field.getName());
                    oField = dField;

                } catch (NoSuchFieldException e) {
                    log.fine(e.toString());
                }
                orderFields.add(order, oField);
            }
        }

        return orderFields;
    }

    /**
     * TODO:Prevedere l'utilizzo di adapter jaxb
     *
     * @param stringMessage
     * @param clazz
     * @param currentField
     * @return
     */
    private Object adapterString(String stringMessage, Class clazz, Field currentField) throws Exception {
        try {

            if (currentField != null && currentField.isAnnotationPresent(HostTypeAdapter.class)) {
                HostTypeAdapter adapter = currentField.getAnnotation(HostTypeAdapter.class);
                Class<? extends HostFieldAdapter> adapterClass = adapter.value();
                HostFieldAdapter hostFieldAdapter = adapterClass.newInstance();
                return hostFieldAdapter.unmarshal(stringMessage);
            }

            if (clazz.equals(String.class)) {

                boolean trimString = true;
                if (currentField != null && currentField.isAnnotationPresent(OffsetLenght.class)) {
                    OffsetLenght annotation = currentField.getAnnotation(OffsetLenght.class);
                    trimString = annotation.trim();
                }
                return (trimString) ? stringMessage.trim() : stringMessage;
            }

            if (clazz.equals(Integer.class)) {
                return Integer.parseInt(stringMessage);
            }

            if (clazz.equals(Date.class)) {

                SimpleDateFormat sourceDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss.SSS");

                try {
                    if (stringMessage.length() > 23) {
                        return sourceDateFormat.parse(stringMessage.substring(0, 23));
                    }
                    return sourceDateFormat.parse(stringMessage);
                } catch (ParseException e) {
                    log.severe(e.toString());
                }
            }

            if (clazz.equals(EXberEsitTypeDTO.class)) {
                return Enum.valueOf(clazz, stringMessage);
            }

            if (clazz.equals(Boolean.class)) {
                return Boolean.valueOf(stringMessage);
            }
        } catch (Exception e) {
            log.severe(e.toString());
        }
        return null;
    }

}
