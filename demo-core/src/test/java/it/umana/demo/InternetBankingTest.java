package it.umana.demo;

import it.umana.demo.ib.InternetBanking;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InternetBankingTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void eseguiBonifico() {
        Assertions.assertTrue(InternetBanking.eseguiBonifico(),"Il bonifico deve andare a buon fine");
    }
}