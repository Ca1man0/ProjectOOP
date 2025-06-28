// REVISIONATO (OKAY) - VERSIONE 2.0

// RIFERIMENTO AL PATTERN PRESENTE:
// ORDINE 1 (FACTORY PATTERN) - RIF CONCRETE PRODUCT p2-2 - CARNE.JAVA

/**
 * FACTORY PATTERN (CONCRETE PRODUCT):
 * Qui ho un'implementazione dell'interfaccia Product, ovvero Piatto.java, quindi
 * viene specificato un comportamento.
 */

 /**
 * TECNOLOGIE USATE:
 *  - LIST - COLLECTION FRAMEWORK | GENERICS:
 *    --> public CarneR(List<String> ingredienti) --> dichiaro una lista che puo contenere solo oggetti di tipo String
 */

package com.examaiman.factory_exception_R.concrete_abastract_product;
import java.util.List;
import java.util.logging.Logger;

// Ho creato una classe Carne che estende Piatto e implementa un comportamento specifico del metodo prepara, ovvero lo stampaggio della lista di ingredienti
public class CarneR extends PiattoR {
    private static final Logger logger = Logger.getLogger(CarneR.class.getName());

    // Quando chiamo il metodo Carne, gli passo la lista e lo assoccio al nome "Carne"
    public CarneR(List<String> ingredienti) {
        super("Carne", ingredienti);
    }

     /**
      * Qui sto implementando il metodo specifico di Carne, ma per farlo devo sovrascrivere il metodo presente in Piatto
      * che non ha nessuna implementazione. La dicitura @override è un modo per far sapere al compilatore per verificare
      * l'esecuzione della sovrascrittura
      */
    @Override
    public void prepara() {
        System.out.println("Preparazione della carne con: " + ingredienti);
        logger.info("Preparazione della carne con: " + ingredienti);

    }
}