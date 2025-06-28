// REVISIONATO (OKAY) - VERSIONE 2.0

// ORDINE 3 (FACTORY PATTERN) - RIF CONCRETE FACTORY P1-2 - PRIMOFACTORY.JAVA
// ORDINE 1 (EXCEPTION PATTERN) - RIF SERVICE e EXCEPTION SHIELDING PATTERN P1-2 - PRIMOFACTORY.JAVA

package com.examaiman.factory_exception_R.concrete_abstract_factory;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.examaiman.factory_exception_R.FactoryExceptionR;
import com.examaiman.factory_exception_R.concrete_abastract_product.*;

/**
 * FACTORY PATTERN (CONCRETE CREATOR):
 * Questa classe implementa il metodo indicato nel PiattoFactory per creare
 * l'oggetto specifico, ovveril piatto Primo
 */

/**
 * EXCEPTION SHIELDING PATTERN (SERVICE):
 * Questa classe rappresenta il servizio che esegue l'operazione richiesta dal client, in cui
 * si va agestire la logica che può generare eccezioni, ad esempio IllegalArgumentException se 
 * la lista degli ingredienti è vuota oppure UnsupportedOperationException se il tipo di piatto non
 * è valido
 * 
 * Service --> sono le operazioni di creazione piatto in cui possono generare gli errori
 * Exception Shielding Pattern --> è il try-catch presente il metodo per catturare le eccezione e restituire un messaggio specificato
 */

 // Public accessibile da qualsiasi codice e Extends vuol dire che sto ereditando i metodi da PiattoFactory per poi implementarli
public class PrimoFactoryR extends PiattoFactoryR {
    private static final Logger logger = Logger.getLogger(PrimoFactoryR.class.getName());

    /**
     * RIF PATTERN FACTORY (CONCRETE CREATOR):
     * Qui sto implementando il metodo per creare l'oggetto specifico, piatto Primo, specificando il tipo di piatto e gli ingredienti
     * La dicitura @override serve per verificare la sovrascrittura del metodo da parte del compilatore
     */
    @Override
    public PiattoR creaPiatto(String tipo, List<String> ingredienti) {
        
        /**
         * RIF EXCEPTION SHIELDING PATTERN (SERVICE e EXCEPTION SHIELDING PATTERN):
         * Prottego il client, mostrando messaggi facili da capire e non errori tecnici
         * Qui ho svolto 2 controlli, il primo verifica se la lista degli ingredienti non sia nulla o vuota, 
         * invece il secondo controllo la validità del tipo di piatto
         */

        try {

        // CONTROLLO 1 --> Se la lista degli ingredienti è null o vuota allora lancia l'eccezione specifica
        logger.log(Level.FINE, "Sto provando a creare il piatto: ", tipo);
        if (ingredienti == null || ingredienti.isEmpty()) {
            throw new IllegalArgumentException("La lista degli ingredienti non può essere vuota");
        }

        // CONTROLLO 2 --> Se il tipo di piatto non corrisponde ai case lancia l'eccezione specifica
        return switch (tipo.toLowerCase()) { // lowercase serve per gestire maiuscole, rendendo omogeneo l'input
            case "pasta" -> new PastaR(ingredienti); // creo un nuovo oggetto che si basa sulla classe Pasta e lo inizializzo con ingredienti

            // Se il valore non corrisponde ai case citat in precedenza, allora lancia l'eccezione
            default -> throw new UnsupportedOperationException("Tipo di primo piatto non è presente nel menu, ovvero: " + tipo);
        };
        
        // Questo catch mi permette di catturare l'eccezione tecnica che sarà utile allo sviluppatore in fase di debug
        } catch (Exception e) {

            // Stampo l'errore specifico e comprensibile allo sviluppatore
            // Dall'errore "e" (ovvero IllegalArgumentException o UnsupportedOperationException) prendo la sua classe "getClass" e prendo solo il suo nome senza il package
            // ad esempio restituiscimi "IllegalArgumentException" e non"java.lang.IllegalArgumentException"
            // e.getMessage() mi serve per prendere il messaggio che ho dichiarato sopra quando ho l'eccezione
            //System.err.println("La causa dal errore nelal generazione del piatto è il seguente: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            logger.log(Level.SEVERE, "La causa dal errore nelal generazione del piatto è il seguente: " + e.getClass().getSimpleName() + " - " + e.getMessage());

            // Questo permette di avvolgere l'errore lanciato in precedenza, personalizzando per una maggior comprensione
            // quindi conservo l'errore tecnico in caso di approfondimento, ma l'utente vedrà in primis il messaggio generico e comprensibile
            throw new FactoryExceptionR("Attento, c'è stato un problema nella creazione piatto, riprova più tardi.", e);
        }
    }
}