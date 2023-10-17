package it.umana.demo.logic;

import it.umana.demo.spi.dto.MessageConfigurationDTO;
import lombok.extern.java.Log;

import java.util.Date;
import java.util.HashMap;

@Log
public class Config {


    /**
     * Hashmap <key=(codiProc|codiServ|destProc), val=(MessageConfigurationDTO)>
     */
    private HashMap<String, MessageConfigurationDTO> storeHashMap = new HashMap<String, MessageConfigurationDTO>() {{
        put("DEMO|LOG|OUT1", new MessageConfigurationDTO("it.umana.demo.plugin.bo.LoggerBO","CODA_LOG",new Date()));
        put("DEMO|MOCK|OUT2", new MessageConfigurationDTO("it.umana.demo.plugin.bo.MockPluginBO","CODA_MOCK",new Date()));
    }};




    public ActionConfigurationDTO getRESTParameters(ActionConfigurationDTO configuration, String codiProc, String codiServ, String destProc) throws Exception {
        String hashKey = codiProc.concat("|").concat(codiServ).concat("|").concat(destProc);

        if (!storeHashMap.containsKey(hashKey)) {
            throw new Exception(String.format("La chiave di configurazione %s non è presente",hashKey));
        }

        MessageConfigurationDTO messageConfigurationDTO = storeHashMap.get(hashKey);
        configuration.setClasspath(messageConfigurationDTO.getPlugin());
        configuration.setKnowledgeQueueName(messageConfigurationDTO.getQueue());
        configuration.setCanSend(true);
        return configuration;

    }



}
