package it.umana.demo.logic;


import it.umana.demo.spi.HostMessageAdapter;
import it.umana.demo.spi.IDemoPlugin;
import it.umana.demo.spi.dto.GestioneErroriDTO;
import it.umana.demo.spi.dto.HeaderDTO;
import it.umana.demo.spi.dto.HeaderTecnicoDTO;
import it.umana.demo.spi.dto.MessageDTO;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;


/**
 * ScodatoreBO
 *
 */
@Log
@AllArgsConstructor
public class ScodatoreBO {


    private Config RESTService;

    private DemoPluginFactory demoPluginFactory;


    /**
     * pushMessage
     *
     * @param message
     * @return
     */
    public void pushMessage(String message) {

        //creo una risposta generica di default in caso non si riesca neanche ad aprire il messaggio
        HeaderDTO headerResult = HeaderDTOFactory.createHeaderDTO();

        MessageDTO messageDTO;

        HostMessageAdapter hostMessageAdapter = new HostMessageAdapter();

        String resultString = hostMessageAdapter.marshal(headerResult);


        // carica configurazione
        var configuration = new ActionConfigurationDTO();


        try {


            try {
                messageDTO = (MessageDTO) hostMessageAdapter.unmarshal(message, MessageDTO.class);
                HeaderTecnicoDTO headerTecnicoDTO = messageDTO.getHeader().getHeaderTecnicoDTO();
                GestioneErroriDTO gestioneErroriDTO = messageDTO.getHeader().getGestioneErrori();
                headerResult.setGestioneErrori(gestioneErroriDTO);
                headerResult.setHeaderTecnicoDTO(headerTecnicoDTO);
                configuration.setICodiProc(headerTecnicoDTO.getXbbr_codi_proc());
                configuration.setiCodiServ(headerTecnicoDTO.getXbbr_codi_serv());
                configuration.setIDestProc(headerTecnicoDTO.getXbbr_dest_proc());

            } catch (Exception e) {
                log.severe(e.toString());
                throw new ScodatoreMessageException(ScodatoreMessageExceptionErrorCodes.ERRORE_UNMARSHAL, e);
            }

            try {
                configuration = RESTService.getRESTParameters(configuration, configuration.getICodiProc(), configuration.getiCodiServ(), configuration.getiDestProc());
            } catch (Exception e) {
                throw new ScodatoreMessageException(ScodatoreMessageExceptionErrorCodes.ERRORE_XML, e);
            }

            HeaderDTO headerDTO;

            try {
                // Creo un'istanza del plugin indicato nel classpath
                //System.out.print(configuration.getClasspath());
                IDemoPlugin plugin_bo = demoPluginFactory.getDemoPluginByClassName(configuration.getClasspath());

                // Invoco il plugin tramite interfaccia
                headerDTO = plugin_bo.manageMessage(messageDTO);


            } catch (Exception e) {
                log.severe(e.toString());
                throw new ScodatoreMessageException(ScodatoreMessageExceptionErrorCodes.ERRORE_PLUGIN, e);
            }



            try {

                resultString = hostMessageAdapter.marshal(headerResult);

                if (resultString == null || resultString.isEmpty()) {
                    Exception exception = new Exception("Errore marshal");
                    throw new ScodatoreMessageException(ScodatoreMessageExceptionErrorCodes.ERRORE_MSG_NON_VALIDO, exception);
                }
            } catch (ScodatoreMessageException e) {
                log.severe(e.getMessage());
                HeaderDTO tmpHeader = HeaderDTOFactory.createHeaderDTO();
                tmpHeader.setGestioneErrori(e.getGestioneErroriDTO());
                resultString = hostMessageAdapter.marshal(tmpHeader);
            }
        } catch (ScodatoreMessageException e) {
            headerResult.setGestioneErrori(e.getGestioneErroriDTO());
            resultString = hostMessageAdapter.marshal(headerResult);
        } finally {
            if (configuration.getCanSend()) {
                try {
                    MPBReponse mpbReponse = new MPBReponse();
                    mpbReponse.sendMessage(resultString, configuration.getKnowledgeQueueName());
                } catch (Exception e) {
                    log.severe(e.toString());
                    ScodatoreMessageException scodatoreMessageException = new ScodatoreMessageException(ScodatoreMessageExceptionErrorCodes.INVIO_NON_RIUSCITO, e);
                    log.severe(scodatoreMessageException.getErrors());
                }
            }
        }

    }


}
