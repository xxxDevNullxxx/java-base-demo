package it.umana.demo.logic;


import lombok.extern.java.Log;

/**
 * Invio dei flussi di ack/nack.
 *
 */
@Log
public class MPBReponse {



    private static final String SENDING_MESSAGE_S = "<----------------------------Sending message [%s] ----------------------------> ";

    private static final String MESSAGE_SENT_S = "<----------------------------Message   sent  [%s] ----------------------------> ";



    /**
     * Setting the Charset Encoding
     */
    private static final int IBM_EBCDIC_CCSID = 1047;

    private static final String QUEUE_CONNECTION_FACTORY = "java:jboss/jms/ORCHES/QueueConnectionFactory";


    public void sendMessage(String proxiedMessage, String queueName)  {

        try {
            log.info(String.format(SENDING_MESSAGE_S, queueName));
            log.info(proxiedMessage);
            log.info(String.format(MESSAGE_SENT_S,queueName));
        } catch (Exception e1) {
            log.severe(e1.toString());
            e1.printStackTrace();
            throw new RuntimeException("Errore nella gestione del messaggio di outgoing", e1);
        }

    }
}
