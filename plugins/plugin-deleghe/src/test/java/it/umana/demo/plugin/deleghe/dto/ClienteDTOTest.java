package it.umana.demo.plugin.deleghe.dto;

import it.umana.demo.spi.manager.BaseDynamicOccoursManager;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ClienteDTOTest {

    @Test
    void getCdg() {
        BaseDynamicOccoursManager occoursManager = new BaseDynamicOccoursManager();
        val fieldValue = occoursManager.unmarshal("CLIENTI003123456789101212345678910131234567891014", null);
        Assertions.assertNotNull(fieldValue);
    }

}