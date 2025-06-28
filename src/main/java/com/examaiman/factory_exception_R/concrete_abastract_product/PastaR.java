// REVISIONATO (OKAY) - VERSIONE 2.0

// RIFERIMENTO AL PATTERN PRESENTE:
// ORDINE 1 (FACTORY PATTERN) - RIF CONCRETE PRODUCT p1-2 - PASTA.JAVA

/**
 * FACTORY PATTERN (CONCRETE PRODUCT):
 * Qui ho un'implementazione dell'interfaccia Product, ovvero Piatto.java, quindi
 * viene specificato un comportamento.
 */

 /**
 * TECNOLOGIE USATE:
 *  - LIST - COLLECTION FRAMEWORK | GENERICS:
 *    --> public PastaR(List<String> ingredienti) --> dichiaro una lista che puo contenere solo oggetti di tipo String
 */

package com.examaiman.factory_exception_R.concrete_abastract_product;
import java.util.List;
import java.util.logging.Logger;

// Ho creato una classe Pasta che estende Piatto e implementa un comportamento specifico del metodo prepara, ovvero lo stampaggio della lista di ingredienti
public class PastaR extends PiattoR {
    private static final Logger logger = Logger.getLogger(PastaR.class.getName());

    // Quando chiamo il metodo Pasta, gli passo la lista e lo assoccio al nome "Pasta"
    public PastaR(List<String> ingredienti) {
        super("Pasta", ingredienti);
    }

     /**
      * Qui sto implementando il metodo specifico di Pasta, ma per farlo devo sovrascrivere il metodo presente in Piatto
      * che non ha nessuna implementazione. La dicitura @override è un modo per far sapere al compilatore per verificare
      * l'esecuzione della sovrascrittura
      */
    @Override
    public void prepara() {
        System.out.println("Preparazione della pasta con: " + ingredienti);
        logger.info("Preparazione della pasta con: " + ingredienti);

    }
}