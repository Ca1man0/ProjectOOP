package com.examaiman;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.examaiman.factory_exception_R.FactoryExceptionR;
import com.examaiman.factory_exception_R.concrete_abastract_product.PiattoR;
import com.examaiman.factory_exception_R.concrete_abstract_factory.PiattoFactoryR;
import com.examaiman.factory_exception_R.concrete_abstract_factory.PrimoFactoryR;
import com.examaiman.factory_exception_R.concrete_abstract_factory.SecondoFactoryR;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FactoryTest {

    private PiattoFactoryR primoFactory;
    private PiattoFactoryR secondoFactory;
    private List<String> ingredientiPasta;
    private List<String> ingredientiCarne;

    /**
     * Qui faccio la mia preparazione del test, istanziando PrimoFactory e SecondoFactory
     * per la creazione dei piatti. Dopodiche creo le mie liste per gli ingredienti
     * 
     * @Before --> mi serve per far ricordare che questo metodo deve essere eseguito prima
     */
    @Before
    public void setUp() {
        primoFactory = new PrimoFactoryR();
        secondoFactory = new SecondoFactoryR();
        ingredientiPasta = Arrays.asList("Spaghetti", "Pomodoro", "Basilico");
        ingredientiCarne = Arrays.asList("Pollo", "Rosmarino");
    }


    /**
     * TEST 1 "CREAZIONE PIATTO DI PASTA"
     * Questo test controlla la CREAZIONE CORRETTA DEL PIATTO PASTA
     * 
     */
    @Test
    public void testPrimoFactoryCreaPastaCorrettamente() {
        PiattoR piatto = primoFactory.creaPiatto("pasta", ingredientiPasta);
        assertNotNull(piatto); // Controllo se il piatto non è null
        assertEquals("Pasta", piatto.getNome()); // Controllo se il nome del piatto è assegnato correttamente
        assertEquals(ingredientiPasta, piatto.getIngredienti()); // Controllo se gli ingredienti del piatto sono assegnati correttamente
    }

    /**
     * TEST 2 "CREAZIONE PIATTO DI CARNE"
     * Questo test controlla la CREAZIONE CORRETTA DEL PIATTO DI CARNE
     * 
     */
    @Test
    public void testSecondoFactoryCreaCarneCorrettamente() {
        PiattoR piatto = secondoFactory.creaPiatto("carne", ingredientiCarne);
        assertNotNull(piatto); // Controllo se il piatto non è null
        assertEquals("Carne", piatto.getNome()); // Controllo se il nome del piatto è assegnato correttamente
        assertEquals(ingredientiCarne, piatto.getIngredienti()); // Controllo se gli ingredienti del piatto sono assegnati correttamente
    }

    /**
     * MULTIPLI TEST 3 DI ERRORE:
     * 1) Verifico se mi da errore per un tipo di piatto non valido, ovvero "pizza" per il primo e "pesce" per il secondo
     * 2) Verifico se mi da errore per una lista di ingredienti vuota o null, però il tipo di piatto per il primo è giusto
     * 3) Verifico se mi da errore per una lista di ingredienti vuota "Collections.emptyList()" o null, 
     *    però il tipo di piatto per il secondo è giusto
     */

    // Errore di tipo 1 - per il primo
    @Test(expected = FactoryExceptionR.class)
    public void testPrimoFactoryLanciaEccezionePerTipoNonValido() {
        primoFactory.creaPiatto("pizza", ingredientiPasta);
    }
    // Errore di tipo 1 - per il secondo
    @Test(expected = FactoryExceptionR.class)
    public void testSecondoFactoryLanciaEccezionePerTipoNonValido() {
        secondoFactory.creaPiatto("pesce", ingredientiCarne);
    }
    // Errore di tipo 2 - per il primo - con la lista vuota
    @Test(expected = FactoryExceptionR.class)
    public void testFactoryLanciaEccezionePerIngredientiVuoti() {
        primoFactory.creaPiatto("pasta", Collections.emptyList());
    }
    // Errore di tipo 3 - per il primo - con la lista Null
    @Test(expected = FactoryExceptionR.class)
    public void testFactoryLanciaEccezionePerIngredientiNull() {
        secondoFactory.creaPiatto("carne", null);
    }
}