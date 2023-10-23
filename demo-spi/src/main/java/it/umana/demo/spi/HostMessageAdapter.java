package it.umana.demo.spi;

import it.umana.demo.spi.adapter.HostFieldAdapter;
import it.umana.demo.spi.annotation.*;
import it.umana.demo.spi.dto.EXberEsitTypeDTO;
import it.umana.demo.spi.manager.IDynamicOccoursManager;
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

                    if (field.isAnnotationPresent(OffsetLength.class)) {

                        if (!field.isAccessible()) {
                            field.setAccessible(true);
                        }

                        OffsetLength annotation = field.getAnnotation(OffsetLength.class);
                        int lengthField = annotation.length();

                        String fieldValue = this.marshal(field.get(obj));
                        if (!annotation.any()) {
                            fieldValue = (fieldValue.length() > lengthField) ? fieldValue.substring(0, lengthField) : fieldValue;
                            sb.append(fieldValue);
                            fieldValue = (fieldValue == null) ? "" : fieldValue;
                            for (int i = 0, l = lengthField - fieldValue.length(); i < l; i++) {
                                sb.append(" ");
                            }
                        } else {
                            sb.append(fieldValue);
                        }
                    } else if (field.isAnnotationPresent(DynamicOccours.class)) {

                        DynamicOccours annotation = field.getAnnotation(DynamicOccours.class);
                        IDynamicOccoursManager dynamicOccoursManager = annotation.DynamicOccoursManager;
                        if (dynamicOccoursManager != null) {
                            sb.append(dynamicOccoursManager.marshal(field.get(obj), field));
                            if (field.isAnnotationPresent(LastField.class)) {
                                LastField annotationLF = (LastField) field.getAnnotation(LastField.class);
                                sb.append(annotationLF.terminatorString());
                            }
                        } else {
                            log.warning("DynamicOccoursManager nullo, impossibile serializzare il campo " + field.getName());
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

                    if (field.isAnnotationPresent(OffsetLength.class)) {
                        OffsetLength annotation = field.getAnnotation(OffsetLength.class);
                        int lengthField = annotation.length();
                        int endValue = (annotation.any()) ? message.length() : indexReadFromMessage + lengthField;
                        if (message.length() < endValue) {
                            throw new Exception(String.format("La lunghezza %d non è valida per il campo %s", lengthField, field.toGenericString()));
                        }
                        String partialMessage = message.substring(indexReadFromMessage, endValue);
                        fieldValue = this.unmarshal(partialMessage, field.getType(), field);

                        indexReadFromMessage = endValue;
                    } else if (field.isAnnotationPresent(DynamicOccours.class)) {
                        DynamicOccours annotation = field.getAnnotation(DynamicOccours.class);
                        IDynamicOccoursManager dynamicOccoursDictionaryManager = annotation.DynamicOccoursManager;

                        if (dynamicOccoursDictionaryManager != null) {
                            String partialMessage = message.substring(indexReadFromMessage);

                            fieldValue = dynamicOccoursDictionaryManager.unmarshal(partialMessage, field);

                        } else {
                            log.warning("DynamicOccoursDictionaryManager nullo, impossibile deserializzare il campo " + currentField.getName());
                            fieldValue = null;
                        }
                    }

                    if (field.isAnnotationPresent(DynamicOccours.class)) {

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
                if (currentField != null && currentField.isAnnotationPresent(OffsetLength.class)) {
                    OffsetLength annotation = currentField.getAnnotation(OffsetLength.class);
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
