package it.umana.demo;

import it.umana.demo.dto.LombokDTO;
import it.umana.demo.dto.RecordDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class dtoTest {

    public static final String STRING_DEMO = "Stringa demo";

    @Test
    void recordTest() {
        Date data = new Date();
        var record = new RecordDTO(STRING_DEMO, data,Integer.MAX_VALUE);
        Assertions.assertEquals(STRING_DEMO,record.stringa());
        Assertions.assertEquals(data,record.data());
        Assertions.assertEquals(Integer.MAX_VALUE,record.integer());
    }

    @Test
    void lombockTest() {
        Date data = new Date();

        var record = new LombokDTO(STRING_DEMO, data,Integer.MAX_VALUE);



        Assertions.assertEquals(STRING_DEMO,record.getStringa());
        Assertions.assertEquals(data,record.getData());
        Assertions.assertEquals(Integer.MAX_VALUE,record.getInteger());
    }
}
