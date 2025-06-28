// REVISIONATO (OKAY) - VERSIONE 2.0

// ORDINE 0 (EXCEPTION PATTERN) - RIF EXCEPTION SHIELDING PATTERN (schermata) - FACTORYEXCEPTION.JAVA
// Vedi prima il PrimoFactory e SecondoFactory

package com.examaiman.factory_exception_R;

/**
 * OSSERVAZIONE IMPORTANTE:
 * Questa parte di codice mi serve per costruire l'eccezione personalizzata,
 * infatti estendo la classe RunTimeEception per poi cambiarla
 */
public class FactoryExceptionR extends RuntimeException {
    
    /**
     * MESSAGGIO SEMPLICE --> PER IL CLIENT:
     * Chiamo il costruttore madre RuntimeException (con il super) e passo il mio messaggio semplice per il client
     */
    public FactoryExceptionR(String message) {
        super(message);
    }
    
    /**
     * MESSAGGIO ESAUSTIVO (MSG SEMPLICE + ERRORE TECNICO) --> SIA PER IL CLIENT CHE LO SVILUPPATORE:
     * Qui invece sto registrando sia il messaggio personalizzato che il dettaglio dell'errore specifico
     * quindi non è solo "Hai sbagliato il tipo di piatto" ma viene anche registrato un errore piu tecnico che potrebbe
     * essere per esempio il IllegalArgumentException e UnsupportedOperationException che verrà analizzato da parte dello sviluppatore
     */
    public FactoryExceptionR(String message, Throwable cause) {
        super(message, cause);
    }
}