package it.umana.demo.spi.manager;

import it.umana.demo.spi.HostMessageAdapter;
import it.umana.demo.spi.annotation.DynamicOccours;
import it.umana.demo.spi.annotation.HostStringMessage;
import it.umana.demo.spi.annotation.OffsetLength;
import lombok.extern.java.Log;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@Log
public class BaseDynamicOccoursManager implements IDynamicOccoursManager {


    private static DecimalFormat decimalFormat;
    private static int NUMBER_OF_RECORD_IN_BLOCK_LEN;

    private HostMessageAdapter hostMessageAdapter;

    static {
        decimalFormat = new DecimalFormat("000");
        NUMBER_OF_RECORD_IN_BLOCK_LEN = decimalFormat.format(0).length();
    }

    private static final String OCCURS_ID_PATTERN = "%-8s";

    public BaseDynamicOccoursManager() {
        this.hostMessageAdapter = new HostMessageAdapter();
    }


    public String marshal(Object obj, Field field) {
        StringBuilder sb = new StringBuilder();

        if (!(obj instanceof ArrayList)) {
            throw new IllegalArgumentException("Era attesa un'ArrayList");
        }

        if (!field.isAnnotationPresent(DynamicOccours.class)) {
            throw new IllegalArgumentException("Era attesa una DynamicOccours");
        }

        DynamicOccours annotation = (DynamicOccours) field.getAnnotation(DynamicOccours.class);
        Type genericFieldType = field.getGenericType();

        if ((genericFieldType instanceof ParameterizedType)) {
            ParameterizedType aType = (ParameterizedType) genericFieldType;
            Type[] fieldArgTypes = aType.getActualTypeArguments();

            if (fieldArgTypes.length != 1) {
                throw new IllegalArgumentException("Era attesa una lista parametrizzata");
            }

            Class fieldArgClass = (Class) fieldArgTypes[0];

            String occoursId = getOccourId(annotation, fieldArgClass);

            log.info(String.format("Aggiungo occourId [%s]", new Object[]{occoursId}));
            sb.append(occoursId);

            ArrayList occursList = (ArrayList) obj;
            int numberOfRecordInBlock = occursList.size();


            String occursNr = decimalFormat.format(numberOfRecordInBlock);

            log.info(String.format("Aggiungo occursNr [%s]", new Object[]{occursNr}));
            sb.append(occursNr);

            for (Object o_record : occursList) {
                String s_record = this.hostMessageAdapter.marshal(o_record);
                if (log.isLoggable(Level.FINE)) {
                    log.fine(String.format("Aggiungo record recuperato -> %s", new Object[]{s_record}));
                }
                sb.append(s_record);
            }
        }

        return sb.toString();
    }

    @Override
    public <T> List<T> unmarshal(String message, Field field) {

        if (!field.isAnnotationPresent(DynamicOccours.class)) {
            throw new IllegalArgumentException("Era attesa una DynamicOccours");
        }

        DynamicOccours annotation = (DynamicOccours) field.getAnnotation(DynamicOccours.class);
        Type genericFieldType = field.getGenericType();

        if (genericFieldType instanceof ParameterizedType) {
            ParameterizedType aType = (ParameterizedType) genericFieldType;
            Type[] fieldArgTypes = aType.getActualTypeArguments();

            if (fieldArgTypes.length != 1) {
                throw new IllegalArgumentException("Era attesa una lista parametrizzata");
            }

            Class fieldArgClass = (Class) fieldArgTypes[0];

            String occoursId = getOccourId(annotation, fieldArgClass);

            int occursStartIndex = message.indexOf(occoursId);

            if (occursStartIndex < 0) {
                if (log.isLoggable(Level.FINE)) {
                    log.fine(String.format("Blocco dati con occursId = [%s] non trovato", new Object[]{occoursId}));
                }
                return null;
            }


            try {
                String currentBlockMessage = message.substring(occursStartIndex + occoursId.length());
                String s_numberOfRecordInBlock = currentBlockMessage.substring(0, NUMBER_OF_RECORD_IN_BLOCK_LEN);
                int numberOfRecordInBlock = decimalFormat.parse(s_numberOfRecordInBlock).intValue();
                currentBlockMessage = currentBlockMessage.substring(NUMBER_OF_RECORD_IN_BLOCK_LEN);
                return unmarshalNamedList(currentBlockMessage, numberOfRecordInBlock, fieldArgClass);
            } catch (Exception e) {
                String messageError = String.format("Errore nel recupero delle informazioni del blocco con occursId = [%s]", new Object[]{occoursId});
                log.severe(messageError);
                throw new IllegalStateException(messageError, e);
            }


        }

        return null;
    }

    private String getOccourId(DynamicOccours annotation, Class fieldArgClass) {
        String occoursId = annotation.occoursId().trim();
        String dbgMessage = "Utilizzo occoursId";

        if (occoursId.length() == 0) {
            occoursId = fieldArgClass.getSimpleName();
            dbgMessage = "OccoursId non valorizzato, utilizzo nome classe";
        }


        occoursId = String.format("%-8s", new Object[]{occoursId});

        log.info(String.format("%s [%s]", new Object[]{dbgMessage, occoursId}));
        return occoursId;
    }

    private <T> List<T> unmarshalNamedList(String message, int numberOfRecordInBlock, Class fieldArgClass) {
        List<T> list = new ArrayList(numberOfRecordInBlock);

        if (fieldArgClass.isAnnotationPresent(HostStringMessage.class)) {
            int recLen = getRecLen(fieldArgClass);

            for (int recNum = 0; recNum < numberOfRecordInBlock; recNum++) {
                if (message.length() < recLen * (recNum + 1)) {
                    throw new IllegalStateException(String.format("Il record numero %d della lista supera la lunghezza %d del messaggio", new Object[]{Integer.valueOf(recNum), Integer.valueOf(message.length())}));
                }

                String s_record = message.substring(recLen * recNum, recLen * (recNum + 1));
                if (log.isLoggable(Level.FINEST)) {
                    log.finest(String.format("Record recuperato -> %s", new Object[]{s_record}));
                }
                list.add(this.hostMessageAdapter.<T>unmarshal(s_record, fieldArgClass));
            }
        } else {
            throw new IllegalArgumentException(String.format("La classe %s della lista di occorrenze passata non ha l'annotazione HostStringMessage", new Object[]{fieldArgClass.toString()}));
        }

        return list;
    }

    private int getRecLen(Class fieldArgClass) {
        HostStringMessage annotation = (HostStringMessage) fieldArgClass.getAnnotation(HostStringMessage.class);
        int recLen = annotation.messageLength();

        log.fine(String.format("Lunghezza della classe %s settata in HostStringMessage -> %d", new Object[]{fieldArgClass.toString(), Integer.valueOf(recLen)}));

        if (recLen <= 0) {
            log.fine(String.format("Lunghezza non valida / non settata in HostStringMessage, tento recupero da somma lunghezze sottocampi", new Object[0]));

            List<Field> orderFieldsList = this.hostMessageAdapter.getFieldListSortedByOrderAnnotation(fieldArgClass);
            recLen = 0;

            for (Field field : orderFieldsList) {
                if (field.isAnnotationPresent(OffsetLength.class)) {
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }

                    int lengthField = ((OffsetLength) field.getAnnotation(OffsetLength.class)).length();
                    recLen += lengthField;
                }
            }

            if (recLen <= 0) {
                throw new IllegalArgumentException("Somma lunghezze sottocampi non valida");
            }

            log.fine(String.format("Somma lunghezze sottocampi valida -> %d", new Object[]{Integer.valueOf(recLen)}));
        }
        return recLen;
    }
}